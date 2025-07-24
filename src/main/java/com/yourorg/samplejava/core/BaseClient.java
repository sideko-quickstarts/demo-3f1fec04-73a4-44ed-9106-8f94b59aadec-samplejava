package com.yourorg.samplejava.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import com.yourorg.samplejava.Environment;

import okhttp3.OkHttpClient;

public final class BaseClient {

    private final Map<String, String> baseUrl;
    private static final String DEFAULT_SERVICE_NAME = "__default_service__";

    private final Map<String, String> headers;

    private final Map<String, Supplier<String>> headerSuppliers;

    private final Map<String, AuthProvider> auths;

    private final OkHttpClient httpClient;

    private BaseClient(
        Map<String, String> baseUrl,
        Map<String, String> headers,
        Map<String, Supplier<String>> headerSuppliers,
        Map<String, AuthProvider> auths,
        OkHttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.headers = new HashMap<>();
        this.headers.putAll(headers);
        this.headers.putAll(new HashMap<String, String>() {
            {
                put("x-sideko-sdk-language", "Java");
            }
        });

        this.headerSuppliers = headerSuppliers;
        this.auths = auths;
        this.httpClient = httpClient;
    }

    public String baseUrl() {
        return this.baseUrl(BaseClient.DEFAULT_SERVICE_NAME);
    }

    public String baseUrl(String serviceName) {
        return this.baseUrl.getOrDefault(serviceName, "");
    }

    public Map<String, String> headers(RequestOptions requestOptions) {
        Map<String, String> values = new HashMap<>(this.headers);
        headerSuppliers.forEach((key, supplier) -> {
            values.put(key, supplier.get());
        });

        if (requestOptions != null) {
            values.putAll(requestOptions.getHeaders());
        }

        return values;
    }

    public OkHttpClient httpClient() {
        return this.httpClient;
    }

    public Map<String, AuthProvider> auths() {
        return this.auths;
    }

    public AuthProvider getAuth(String authName) {
        return this.auths.get(authName);
    }

    public OkHttpClient httpClientWithTimeout(RequestOptions requestOptions) {
        if (requestOptions == null) {
            return this.httpClient;
        }

        return this.httpClient
               .newBuilder()
               .callTimeout(requestOptions.getTimeout().get(), requestOptions.getTimeoutTimeUnit())
               .connectTimeout(0, TimeUnit.SECONDS)
               .writeTimeout(0, TimeUnit.SECONDS)
               .readTimeout(0, TimeUnit.SECONDS)
               .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Map<String, String> baseUrl = new HashMap<>();

        private final Map<String, String> headers = new HashMap<>();

        private final Map<String, Supplier<String>> headerSuppliers = new HashMap<>();

        private final Map<String, AuthProvider> auths = new HashMap<>();

        public Builder baseUrl(Environment env) {
            this.baseUrl(env.getUrl());
            return this;
        }

        public Builder baseUrl(String url) {
            this.baseUrl.put(BaseClient.DEFAULT_SERVICE_NAME, url);
            return this;
        }

        public Builder baseUrl(Map<String, String> map) {
            this.baseUrl = map;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder addHeader(String key, Supplier<String> value) {
            this.headerSuppliers.put(key, value);
            return this;
        }

        public Builder addAuth(String name, AuthProvider provider) {
            this.auths.put(name, provider);
            return this;
        }

        public BaseClient build() {
            OkHttpClient okhttpClient = new OkHttpClient.Builder()
            .addInterceptor(new RetryInterceptor(3))
            .build();
            return new BaseClient(baseUrl, headers, headerSuppliers, auths, okhttpClient);
        }
    }
}
