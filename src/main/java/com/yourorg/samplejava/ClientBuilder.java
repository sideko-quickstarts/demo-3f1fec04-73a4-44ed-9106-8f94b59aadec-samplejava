
package com.yourorg.samplejava;

import com.yourorg.samplejava.core.AuthKey;
import com.yourorg.samplejava.core.AuthKeyLocation;
import com.yourorg.samplejava.core.BaseClient;


public final class ClientBuilder {

    private final BaseClient.Builder baseClientBuilder = BaseClient.builder();
    private Environment environment = Environment.ENVIRONMENT;
    public ClientBuilder environment(Environment environment) {
        this.environment = environment;
        return this;
    }
    public ClientBuilder url(String url) {
        this.environment = Environment.custom(url);
        return this;
    }
    public Client build() {
        baseClientBuilder.baseUrl(this.environment);
        return new Client(baseClientBuilder.build());
    }

    public ClientBuilder withApiKey(String apiKey) {
        this.baseClientBuilder.addAuth("api_key", new AuthKey(AuthKeyLocation.HEADER_KEY, "api_key",
                                       apiKey));
        return this;
    }

}
