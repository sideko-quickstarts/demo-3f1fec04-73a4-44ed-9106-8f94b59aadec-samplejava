
package com.yourorg.samplejava.resources.store.order.params;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * GetRequest
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = GetRequest.Builder.class)
public final class GetRequest {
    /**
     * ID of order that needs to be fetched
     */
    private final Integer orderId;

    private GetRequest(
        Integer orderId
    ) {
        this.orderId = orderId;
    }

    /**
     * Retrieves value for `orderId`
     * ID of order that needs to be fetched
     */
    @JsonProperty("orderId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getOrderId() {
        return orderId;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof GetRequest && equalTo((GetRequest) other);
    }

    private boolean equalTo(GetRequest other) {
        return
            orderId.equals(other.orderId)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.orderId
               );
    }

    @Override
    public String toString() {
        return "GetRequest{" +
               "orderId=" + orderId +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        /**
         * ID of order that needs to be fetched
         */
        private Integer orderId = null;

        private Builder() {}

        public Builder from(GetRequest other) {
            orderId(other.getOrderId());
            return this;
        }

        /**
         * Sets value for `orderId`
         * ID of order that needs to be fetched
         */
        @JsonSetter("orderId")
        public Builder orderId(Integer orderId) {
            if (orderId == null) {
                throw new IllegalArgumentException("orderId cannot be null");
            }

            this.orderId = orderId;

            return this;
        }



        public GetRequest build() {
            if (orderId == null) {
                throw new IllegalStateException("orderId is required");
            }

            return new GetRequest(
                       orderId
                   );
        }
    }

}



