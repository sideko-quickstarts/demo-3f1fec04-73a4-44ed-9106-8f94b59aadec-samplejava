package com.yourorg.samplejava.core;

import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.Response;

public class OAuth2 implements AuthProvider {

    // OAuth2 provider configuration
    private final String baseUrl;
    private final String defaultTokenUrl;
    private final String accessTokenPointer;
    private final String expiresInPointer;
    private final String credentialsLocation;
    private final String bodyContent;
    private final AuthProvider requestMutator;
    private final OAuth2Form form;

    // access token retention
    private Optional<String> accessToken;
    private Optional<ZonedDateTime> expiresAt;

    public OAuth2(
        String baseUrl,
        String defaultTokenUrl,
        String accessTokenPointer,
        String expiresInPointer,
        String credentialsLocation,
        String bodyContent,
        AuthProvider requestMutator,
        OAuth2Form form
    ) {
        this.baseUrl = baseUrl;
        this.defaultTokenUrl = defaultTokenUrl;
        this.accessTokenPointer = accessTokenPointer;
        this.expiresInPointer = expiresInPointer;
        this.credentialsLocation = credentialsLocation;
        this.bodyContent = bodyContent;
        this.requestMutator = requestMutator;
        this.form = form;
        this.accessToken = null;
        this.expiresAt = null;
    }

    @Override
    public void setValue(String val) {
        throw new UnsupportedOperationException("OAuth cannot be used as a requestMutator");
    }

    @Override
    public HttpRequestBuilder addAuth(HttpRequestBuilder builder) {
        if (this.form.getUsername() == null && this.form.getPassword() == null &&
                this.form.getClientId() == null && this.form.getClientSecret() == null) {
            // auth provider is not configured to make token requests
            return builder;
        }

        String token;

        try {
            token = this.getAccessToken();
        } catch (IOException ex) {
            throw new ApiException("Error executing token HTTP request", ex);
        }

        this.requestMutator.setValue(token);

        return this.requestMutator.addAuth(builder);
    }

    private String getAccessToken() throws IOException {
        if (this.accessToken != null) {
            ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

            if (this.expiresAt != null
                    && this.expiresAt.get().isBefore(now)) {
                return this.refresh();
            }

            return this.accessToken.get();
        }

        return this.refresh();
    }

    private String refresh() throws IOException {
        String tokenUrl = this.defaultTokenUrl;

        if (this.form.getTokenUrl() != null) {
            tokenUrl = this.form.getTokenUrl().get();
        }

        if (tokenUrl.startsWith("/")) {
            String base = this.baseUrl.endsWith("/") ? this.baseUrl.substring(0,
                          this.baseUrl.length() - 1) : this.baseUrl;
            String path = tokenUrl.substring(1);
            tokenUrl = base + "/" + path;
        }

        HttpRequestBuilder builder = new HttpRequestBuilder().method("POST").baseUrl(tokenUrl).path("");

        boolean basicAuthCreds = "basic_authorization_header".equals(this.credentialsLocation);
        Optional<String> clientId = this.form.getClientId();
        Optional<String> clientSecret = this.form.getClientSecret();
        Optional<String> username = this.form.getUsername();
        Optional<String> password = this.form.getPassword();
        Optional<List<String>> scope = this.form.getScope();

        if (basicAuthCreds && (this.form.getClientId() != null || this.form.getClientSecret() != null)) {
            String clientIdStr = clientId == null ? "" : clientId.get();
            String clientSecretStr = clientSecret == null ? "" : clientSecret.get();
            String encodedAuth = Base64.getEncoder().encodeToString((clientIdStr + ":" +
                                 clientSecretStr).getBytes());
            builder.addHeader("Authorization", "Basic " + encodedAuth);
        }

        Map<String, String> data = new HashMap();
        data.put("grant_type", this.form.getGrantType());

        if (!basicAuthCreds && clientId != null) {
            data.put("client_id", clientId.get());
        }

        if (!basicAuthCreds && clientSecret != null) {
            data.put("client_secret", clientSecret.get());
        }

        if (username != null) {
            data.put("username", username.get());
        }

        if (password != null) {
            data.put("password", password.get());
        }

        if (scope != null) {
            data.put("scope", scope.get().stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        if ("json".equals(this.bodyContent)) {
            builder.setJsonBody(data);
        } else {
            builder.setFormUrlEncodedBody(data, new HashMap<>(), new HashMap<>());
        }

        Response res = builder.execute();
        JsonNode resData = ResponseHandler.processJsonResponse(res, new TypeReference<ObjectNode>() {
        });

        JsonNode tokenNode = resData.at(this.accessTokenPointer);

        if (tokenNode.isMissingNode() || tokenNode.getNodeType() != JsonNodeType.STRING) {
            throw new IllegalArgumentException("No access token in OAuth response at pointer " +
                                               this.accessTokenPointer);
        }

        String token = tokenNode.asText();
        this.accessToken = Optional.of(token);

        JsonNode expiryNode = resData.at(this.expiresInPointer);
        Integer expiresInSecs = expiryNode.asInt(600);
        this.expiresAt = Optional.of(ZonedDateTime.now(ZoneOffset.UTC).plusSeconds(expiresInSecs));

        return token;
    }

}
