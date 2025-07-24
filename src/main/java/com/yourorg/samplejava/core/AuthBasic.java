package com.yourorg.samplejava.core;

import java.util.Base64;

public class AuthBasic implements AuthProvider {

    private String username;
    private final String password;

    public AuthBasic(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public HttpRequestBuilder addAuth(HttpRequestBuilder builder) {
        String encodedAuth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        builder.addHeader("Authorization", "Basic " + encodedAuth);
        return builder;
    }

    @Override
    public void setValue(String val) {
        this.username = val;
    }
}
