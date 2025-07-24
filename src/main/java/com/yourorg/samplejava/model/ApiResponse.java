
package com.yourorg.samplejava.model;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * ApiResponse
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = ApiResponse.Builder.class)
public final class ApiResponse {
    private final Optional<Integer> code;
    private final Optional<String> message;
    private final Optional<String> type;

    private ApiResponse(
        Optional<Integer> code,
        Optional<String> message,
        Optional<String> type
    ) {
        this.code = code;
        this.message = message;
        this.type = type;
    }

    /**
     * Retrieves value for `code`
     */
    @JsonProperty("code")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getCode() {
        return code.orElse(null);
    }

    /**
     * Retrieves value for `message`
     */
    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public String getMessage() {
        return message.orElse(null);
    }

    /**
     * Retrieves value for `type`
     */
    @JsonProperty("type")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public String getType() {
        return type.orElse(null);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof ApiResponse && equalTo((ApiResponse) other);
    }

    private boolean equalTo(ApiResponse other) {
        return
            code.equals(other.code) &&

            message.equals(other.message) &&

            type.equals(other.type)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.code,
                   this.message,
                   this.type
               );
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
               "code=" + code +
               "message=" + message +
               "type=" + type +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Optional<Integer> code = Optional.empty();
        private Optional<String> message = Optional.empty();
        private Optional<String> type = Optional.empty();

        private Builder() {}

        public Builder from(ApiResponse other) {
            code(other.getCode());
            message(other.getMessage());
            type(other.getType());
            return this;
        }

        /**
         * Sets value for `code`
         */
        @JsonSetter("code")
        public Builder code(Integer code) {
            this.code = Optional.ofNullable(code);

            return this;
        }

        public Builder code(Optional<Integer> code) {
            this.code = code;
            return this;
        }

        /**
         * Sets value for `message`
         */
        @JsonSetter("message")
        public Builder message(String message) {
            this.message = Optional.ofNullable(message);

            return this;
        }

        public Builder message(Optional<String> message) {
            this.message = message;
            return this;
        }

        /**
         * Sets value for `type`
         */
        @JsonSetter("type")
        public Builder type(String type) {
            this.type = Optional.ofNullable(type);

            return this;
        }

        public Builder type(Optional<String> type) {
            this.type = type;
            return this;
        }


        public ApiResponse build() {
            return new ApiResponse(
                       code,
                       message,
                       type
                   );
        }
    }

}



