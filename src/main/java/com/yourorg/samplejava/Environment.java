
package com.yourorg.samplejava;



public final class Environment {

    private final String url;
    public static final Environment ENVIRONMENT = new
    Environment("https://petstore3.swagger.io/api/v3");
    public static final Environment MOCK_SERVER = new
    Environment("https://api.sideko-staging.dev/v1/mock/demo/sample/0.1.0");

    private Environment(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public static Environment custom(String url) {
        return new Environment(url);
    }
}
