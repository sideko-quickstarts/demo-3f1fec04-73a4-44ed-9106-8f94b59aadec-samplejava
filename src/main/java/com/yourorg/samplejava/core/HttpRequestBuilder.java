package com.yourorg.samplejava.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kotlin.NotImplementedError;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRequestBuilder {

    private final Request.Builder requestBuilder;
    private final OkHttpClient.Builder clientBuilder;
    private final Map<String, String> pathParams;
    private final Map<String, List<String>> queryParams;
    private String baseUrl;
    private String urlPath;
    private Optional<Integer> timeout;
    private TimeUnit timeoutTimeUnit;
    private final ObjectMapper objectMapper;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private HttpMethod method;
    private RequestBody body;

    public enum HttpMethod {
        GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS;

        public static HttpMethod fromString(String method) {
            try {
                return valueOf(method.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unsupported HTTP method: " + method, e);
            }
        }
    }

    public HttpRequestBuilder() {
        this.requestBuilder = new Request.Builder();
        this.clientBuilder = new OkHttpClient.Builder();
        this.pathParams = new HashMap<>();
        this.queryParams = new HashMap<>();
        this.timeout = Optional.empty();
        this.timeoutTimeUnit = TimeUnit.SECONDS;
        this.objectMapper = new ObjectMapper();
    }

    public HttpRequestBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public HttpRequestBuilder path(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public HttpRequestBuilder addPathParam(String key, Object value) {
        this.pathParams.put(key, String.valueOf(value));
        return this;
    }

    public HttpRequestBuilder addQueryParam(String key, Object value, String style, boolean explode) {
        return this.encodeUrlParam(this.queryParams, key, value, style, explode);
    }

    private void addToMapArray(Map<String, List<String>> mapArr, String key, String val) {
        mapArr.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
    }

    public static boolean isPrimitive(Object value) {
        if (value == null) {
            return false;
        }

        return value instanceof Number || value instanceof Boolean || value instanceof Character
               || value instanceof Byte || value instanceof Short || value instanceof Integer
               || value instanceof Long || value instanceof Float || value instanceof Double
               || value instanceof String;
    }

    private HttpRequestBuilder encodeUrlParam(Map<String, List<String>> mapArr, String key,
            Object value, String style, boolean explode) {
        switch (style) {
            case "form":
                return this.encodeFormParam(mapArr, key, value, explode);

            case "spaceDelimited":
                return this.encodeSpaceDelimitedParam(mapArr, key, value, explode);

            case "pipeDelimited":
                return this.encodePipeDelimitedParam(mapArr, key, value, explode);

            case "deepObject":
                return this.encodeDeepObjectParam(mapArr, key, value, explode);

            default:
                throw new NotImplementedError(String.format("query param style `%s` not implemented", style));
        }
    }

    private HttpRequestBuilder encodeFormParam(Map<String, List<String>> mapArr, String key,
            Object value, boolean explode) {
        if (value == null) {
            return this;
        } else if (isPrimitive(value)) {
            this.addToMapArray(mapArr, key, value.toString());
            // this.insertQueryParam(key, value.toString());
        } else if (value instanceof List) {
            List<Object> listVal = (List<Object>) value;

            if (explode) {
                // explode form lists should be encoded like /users?id=3&id=4&id=5
                for (Object listItem : listVal) {
                    this.encodeFormParam(mapArr, key, listItem, explode);
                }
            } else {
                // non-explode form lists should be encoded like /users?id=3,4,5
                String nonExploded = listVal.stream().map(String::valueOf).collect(Collectors.joining(","));
                // this.insertQueryParam(key, nonExploded);
                this.addToMapArray(mapArr, key, nonExploded);
            }
        } else if (value instanceof Map) {
            Map<String, Object> mapVal = (Map<String, Object>) value;

            if (explode) {
                // explode form objects should be encoded like /users?key0=val0&key1=val1
                // the input param name will be omitted
                for (Map.Entry<String, Object> entry : mapVal.entrySet()) {
                    this.addToMapArray(mapArr, entry.getKey(), String.valueOf(entry.getValue()));
                    // insertQueryParam(entry.getKey(), String.valueOf(entry.getValue()));
                }
            } else {
                // non-explode form objects should be encoded like /users?id=key0,val0,key1,val1
                List<String> chunks = new ArrayList();

                for (Map.Entry<String, Object> entry : mapVal.entrySet()) {
                    chunks.add(entry.getKey());
                    chunks.add(String.valueOf(entry.getValue()));
                }

                // insertQueryParam(key, chunks.stream().collect(Collectors.joining(",")));
                this.addToMapArray(mapArr, key, chunks.stream().collect(Collectors.joining(",")));
            }
        } else {
            // complex object, serialize it into json string & back into Object to handle
            try {
                String jString = this.objectMapper.writeValueAsString(value);
                Object jObject = this.objectMapper.readValue(jString, Object.class);
                this.encodeFormParam(mapArr, key, jObject, explode);
            } catch (JsonProcessingException e) {
                System.err.println("Failed serializing object " + value + "\n" + e);
            }
        }

        return this;

    }

    private HttpRequestBuilder encodeSpaceDelimitedParam(Map<String, List<String>> mapArr, String key,
            Object value, boolean explode) {
        if (value instanceof List && !explode) {
            // non-explode spaceDelimited lists should be encoded like /users?id=3%204%205
            List<Object> listVal = (List<Object>) value;
            String spaceDelimited = listVal.stream().map(String::valueOf).collect(Collectors.joining(" "));
            this.addToMapArray(mapArr, key, spaceDelimited);
            return this;
        } else {
            // according to the docs, spaceDelimited + explode=false only effects lists,
            // all other encodings are marked as n/a or are the same as `form` style
            // fall back on form style as it is the default for query params
            return this.encodeFormParam(mapArr, key, value, explode);
        }

    }

    private HttpRequestBuilder encodePipeDelimitedParam(Map<String, List<String>> mapArr, String key,
            Object value, boolean explode) {
        if (value instanceof List && !explode) {
            // non-explode pipeDelimited lists should be encoded like /users?id=3|4|5
            List<Object> listVal = (List<Object>) value;
            String pipeDelimited = listVal.stream().map(String::valueOf).collect(Collectors.joining("|"));
            this.addToMapArray(mapArr, key, pipeDelimited);
            return this;
        } else {
            // according to the docs, pipeDelimited + explode = false only effects lists
            // all other encodings are marked as n / a or are the same as `form` style
            // fall back on form style as it is the default for query params
            return this.encodeFormParam(mapArr, key, value, explode);
        }

    }

    private HttpRequestBuilder encodeDeepObjectParam(Map<String, List<String>> mapArr, String key,
            Object value, boolean explode) {
        if (value == null) {
            return this;
        } else if (isPrimitive(value)) {
            // according to the docs, deepObject style only applies to
            // object encodes, encodings for primitives & arrays are listed as n/a,
            // fall back on form style as it is the default for query params
            return this.encodeFormParam(mapArr, key, value, explode);
        } else {
            return this.encodeDeepObjectKey(mapArr, key, value);
        }

    }

    private HttpRequestBuilder encodeDeepObjectKey(Map<String, List<String>> mapArr, String key,
            Object value) {
        if (isPrimitive(value)) {
            this.addToMapArray(mapArr, key, String.valueOf(value));
        } else if (value instanceof List) {
            List<Object> listVal = (List<Object>) value;
            int idx = 0;

            for (Object listItem : listVal) {
                this.encodeDeepObjectKey(mapArr, key + "[" + idx + "]", listItem);
                idx++;

            }
        } else if (value instanceof Map) {
            Map<String, Object> mapVal = (Map<String, Object>) value;

            for (Map.Entry<String, Object> entry : mapVal.entrySet()) {
                String mapKey = entry.getKey();
                this.encodeDeepObjectKey(mapArr, key + "[" + mapKey + "]", entry.getValue());
            }
        } else {
            // complex object, serialize it into json string & back into Object to handle
            try {
                String jString = this.objectMapper.writeValueAsString(value);
                Object jObject = this.objectMapper.readValue(jString, Object.class);
                this.encodeDeepObjectKey(mapArr, key, jObject);
            } catch (JsonProcessingException e) {

                System.err.println("Failed serializing object " + value + "\n" + e);
            }
        }

        return this;
    }

    public HttpRequestBuilder addAuth(AuthProvider provider) {

        if (provider != null) {
            return provider.addAuth(this);
        }

        return this;
    }

    public HttpRequestBuilder method(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpRequestBuilder method(String method) {
        this.method = HttpMethod.fromString(method);
        return this;
    }

    public HttpRequestBuilder setJsonBody(Object body) throws IOException {
        if (body != null) {
            String jsonBody = objectMapper.writeValueAsString(body);
            this.body = RequestBody.create(jsonBody, JSON);
        }

        return this;
    }

    private void addObjectToMultipartBody(MultipartBody.Builder multipartBuilder, Object obj,
                                          String mediaTypeString) {
        for (Method declaredMethod : obj.getClass().getDeclaredMethods()) {
            JsonProperty jsonProperty = declaredMethod.getAnnotation(JsonProperty.class);

            if (jsonProperty != null) {
                String fieldName = jsonProperty.value();

                try {
                    Object value = declaredMethod.invoke(obj);
                    addPartToMultipartBody(multipartBuilder, fieldName, value, mediaTypeString);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Cannot construct form data from type", e);
                }
            }
        }
    }

    public HttpRequestBuilder setMultipartBody(Object formData, String mediaTypeString) {
        if (formData != null) {
            MultipartBody.Builder multipartBuilder = new MultipartBody.Builder()
            .setType(MultipartBody.FORM);

            addObjectToMultipartBody(multipartBuilder, formData, mediaTypeString);

            this.body = multipartBuilder.build();
        }

        return this;
    }

    private void addPartToMultipartBody(MultipartBody.Builder multipartBuilder, String key,
                                        Object value, String mediaTypeString) {
        if (value == null) {
            return;
        }

        if (value instanceof File) {
            File file = (File) value;
            multipartBuilder.addFormDataPart(key, file.getName(),
                                             RequestBody.create(file, MediaType.parse(mediaTypeString)));
        } else if (value instanceof String) {
            multipartBuilder.addFormDataPart(key, (String) value);
        } else if (value instanceof Collection) {
            for (Object item : (Collection<?>) value) {
                addPartToMultipartBody(multipartBuilder, key, item, mediaTypeString);
            }
        } else {
            multipartBuilder.addFormDataPart(key, String.valueOf(value));
        }
    }

    public HttpRequestBuilder setBinaryBody(File file, String mediaTypeString) throws IOException {
        if (file != null) {
            MediaType mediaType = MediaType.parse(mediaTypeString);
            this.body = RequestBody.create(file, mediaType);
        }

        return this;
    }

    public HttpRequestBuilder setRawBody(String content, String mediaTypeString) {
        if (content != null) {
            MediaType mediaType = MediaType.parse(mediaTypeString);
            this.body = RequestBody.create(content, mediaType);
        }

        return this;
    }

    public HttpRequestBuilder setFormUrlEncodedBody(Object value, Map<String, String> styleMap,
            Map<String, Boolean> explodeMap) throws IOException {
        if (value == null) {
            return this;
        } else if (isPrimitive(value) || value instanceof List) {
            throw new IllegalArgumentException("x-www-form-urlencoded data must be an object at the top level");
        } else if (value instanceof Map) {
            Map<String, List<String>> formUrlBody = new HashMap<>();
            Map<String, Object> mapVal = (Map<String, Object>) value;

            // encode according to property style / explode settings
            for (Map.Entry<String, Object> entry : mapVal.entrySet()) {
                String mapKey = entry.getKey();
                String style = styleMap.getOrDefault(mapKey, "form");
                Boolean explode = explodeMap.getOrDefault(mapKey, style.equals("form"));

                this.encodeUrlParam(formUrlBody, mapKey, entry.getValue(), style, explode);
            }

            FormBody.Builder builder = new FormBody.Builder();

            for (Map.Entry<String, List<String>> entry : formUrlBody.entrySet()) {
                for (String val : entry.getValue()) {
                    builder.add(entry.getKey(), val);
                }
            }

            this.body = builder.build();
        } else {
            // complex object, serialize it into json string & back into Object to handle
            try {
                String jString = this.objectMapper.writeValueAsString(value);
                Object jObject = this.objectMapper.readValue(jString, Object.class);
                this.setFormUrlEncodedBody(jObject, styleMap, explodeMap);

            } catch (JsonProcessingException e) {
                System.err.println("Failed serializing object " + value + "\n" + e);
            }

        }

        return this;
    }

    public HttpRequestBuilder addHeader(String name, Object value) {
        if (value != null) {
            requestBuilder.addHeader(name, String.valueOf(value));
        }

        return this;

    }

    public HttpRequestBuilder timeout(int timeout) {
        this.timeout = Optional.of(timeout);
        return this;
    }

    public HttpRequestBuilder timeout(int timeout, TimeUnit timeoutTimeUnit) {
        this.timeout = Optional.of(timeout);
        this.timeoutTimeUnit = timeoutTimeUnit;
        return this;
    }

    public Response execute() throws IOException {
        if (baseUrl == null || urlPath == null || method == null) {
            throw new IllegalStateException("baseUrl, path, and method must be set before executing the request.");
        }

        String resolvedPath = urlPath;

        for (Map.Entry<String, String> entry : pathParams.entrySet()) {
            resolvedPath = resolvedPath.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        HttpUrl parsedUrl = HttpUrl.parse(baseUrl);

        if (parsedUrl == null) {
            throw new IllegalArgumentException("Invalid base URL: " + baseUrl);
        }

        HttpUrl.Builder urlBuilder = parsedUrl.newBuilder();

        // Add path segments
        String pathToAdd = resolvedPath.startsWith("/") ? resolvedPath.substring(1) : resolvedPath;

        if (pathToAdd.length() > 0) {
            urlBuilder.addPathSegments(pathToAdd);
        }

        // Add query parameters
        for (Map.Entry<String, List<String>> entry : this.queryParams.entrySet()) {
            String key = entry.getKey();

            for (String param : entry.getValue()) {
                urlBuilder.addQueryParameter(key, param);
            }
        }

        requestBuilder.url(urlBuilder.build());

        switch (method) {
            case GET:
                requestBuilder.get();
                break;

            case POST:
                requestBuilder.post(body != null ? body : RequestBody.create(new byte[0], null));
                break;

            case PUT:
                requestBuilder.put(body != null ? body : RequestBody.create(new byte[0], null));
                break;

            case DELETE:
                requestBuilder.delete(body);
                break;

            case PATCH:
                requestBuilder.patch(body != null ? body : RequestBody.create(new byte[0], null));
                break;

            case HEAD:
                requestBuilder.head();
                break;

            case OPTIONS:
                requestBuilder.method("OPTIONS", null);
                break;
        }

        if (timeout.isPresent()) {
            clientBuilder.callTimeout(timeout.get(), timeoutTimeUnit);
        }

        OkHttpClient client = clientBuilder.build();
        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new ApiException("API returned a non successful error code: " + response.code(), response);
        }

        return response;
    }
}
