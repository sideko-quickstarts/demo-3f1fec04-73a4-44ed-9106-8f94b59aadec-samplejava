package com.yourorg.samplejava.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public final class RequestOptions {
    private final Optional<Integer> timeout;

    private final TimeUnit timeoutTimeUnit;

    private RequestOptions(Optional<Integer> timeout, TimeUnit timeoutTimeUnit) {
        this.timeout = timeout;
        this.timeoutTimeUnit = timeoutTimeUnit;
    }

    public Optional<Integer> getTimeout() {
        return timeout;
    }

    public TimeUnit getTimeoutTimeUnit() {
        return timeoutTimeUnit;
    }

    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        return headers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Optional<Integer> timeout = Optional.empty();

        private TimeUnit timeoutTimeUnit = TimeUnit.SECONDS;

        public Builder timeout(Integer timeout) {
            this.timeout = Optional.of(timeout);
            return this;
        }

        public Builder timeout(Integer timeout, TimeUnit timeoutTimeUnit) {
            this.timeout = Optional.of(timeout);
            this.timeoutTimeUnit = timeoutTimeUnit;
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(timeout, timeoutTimeUnit);
        }
    }
}
