package com.yourorg.samplejava.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ResponseHandler {

    private static final ObjectMapper objectMapper = ObjectMappers.JSON_MAPPER;

    // JSON handling
    public static <T> T processJsonResponse(Response response,
                                            Class<T> responseType) throws ApiException {
        return processJsonResponseInternal(response, objectMapper.constructType(responseType));
    }

    public static <T> T processJsonResponse(Response response,
                                            TypeReference<T> typeReference) throws ApiException {
        return processJsonResponseInternal(response, objectMapper.constructType(typeReference));
    }

    private static <T> T processJsonResponseInternal(Response response,
            JavaType javaType) throws ApiException {
        String responseString = getResponseBodyString(response);

        try {
            return objectMapper.readValue(responseString, javaType);
        } catch (IOException e) {
            throw new ApiException("Error processing JSON response", e);
        }
    }

    // Text handling
    public static String processTextResponse(Response response) throws ApiException {
        return getResponseBodyString(response);
    }

    // Binary handling
    public static byte[] processBinaryResponse(Response response) throws ApiException {
        try (ResponseBody responseBody = response.body()) {
            if (responseBody == null) {
                throw new ApiException("Error processing binary response, response body empty", response);
            }

            return responseBody.bytes();
        } catch (IOException e) {
            throw new ApiException("Error reading binary response", e);
        }
    }

    public static <T> T processMultipleResponseTypes(Response response, List<String> contentTypes,
            Class<T> responseType) throws ApiException {
        MediaType actualContentType = response.body().contentType();

        if (actualContentType == null) {
            throw new ApiException("Response does not have a content type");
        }

        String actualContentTypeString = actualContentType.toString().toLowerCase();

        for (String expectedContentType : contentTypes) {
            if (actualContentTypeString.startsWith(expectedContentType.toLowerCase())) {
                if (expectedContentType.contains("json")) {
                    return processJsonResponse(response, responseType);
                } else if (expectedContentType.contains("text")) {
                    String textResponse = processTextResponse(response);
                    return createResponseInstance(responseType, textResponse);
                } else {
                    byte[] binaryResponse = processBinaryResponse(response);
                    return createResponseInstance(responseType, binaryResponse);
                }
            }
        }

        throw new ApiException("Unsupported content type: " + actualContentTypeString);
    }

    private static <T> T createResponseInstance(Class<T> responseType,
            Object responseData) throws ApiException {
        try {
            // Check if the responseType has a static 'of' method
            Method ofMethod = responseType.getMethod("of", Object.class);

            if (Modifier.isStatic(ofMethod.getModifiers())) {
                return responseType.cast(ofMethod.invoke(null, responseData));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // If 'of' method is not found or cannot be invoked, fall through to next approach
        }

        // If responseType is assignable from the responseData's type, cast directly
        if (responseType.isAssignableFrom(responseData.getClass())) {
            return responseType.cast(responseData);
        }

        // If we can't handle it, throw an exception
        throw new ApiException("Cannot create instance of " + responseType.getName() + " from "
                               + responseData.getClass().getName());
    }

    // Helper method to get response body as string
    private static String getResponseBodyString(Response response) throws ApiException {
        try (Response responseClone = response.newBuilder().build();
                    ResponseBody responseBody = responseClone.body()) {
            if (responseBody == null) {
                String errorBody = responseBody != null ? responseBody.string() : null;
                throw new ApiException(errorBody, response);
            }

            return responseBody.string();
        } catch (IOException e) {
            throw new ApiException("Error reading response body", e);
        }
    }
}
