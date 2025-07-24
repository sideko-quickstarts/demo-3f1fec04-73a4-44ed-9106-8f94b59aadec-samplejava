package com.yourorg.samplejava.core;

public class AuthBearer implements AuthProvider {

    private String token;

    public AuthBearer(String token) {
        this.token = token;
    }

    @Override
    public HttpRequestBuilder addAuth(HttpRequestBuilder builder) {
        builder.addHeader("Authorization", "Bearer " + token);
        return builder;
    }

    @Override
    public void setValue(String val) {
        this.token = val;
    }
}
