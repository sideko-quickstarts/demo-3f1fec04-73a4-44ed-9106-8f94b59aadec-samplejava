package com.yourorg.samplejava.core;

import java.util.List;
import java.util.Optional;

public interface OAuth2Form {

    public String getGrantType();

    public Optional<String> getUsername();

    public Optional<String> getPassword();

    public Optional<String> getClientId();

    public Optional<String> getClientSecret();

    public Optional<List<String>> getScope();

    public Optional<String> getTokenUrl();

}
