package com.yourorg.samplejava.core;

public interface AuthProvider {

    public void setValue(String val);

    public HttpRequestBuilder addAuth(HttpRequestBuilder builder);

}
