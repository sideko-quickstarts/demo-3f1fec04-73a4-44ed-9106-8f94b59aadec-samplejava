
package com.yourorg.samplejava.resources.store.order.params;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * DeleteRequest
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = DeleteRequest.Builder.class)
public final class DeleteRequest {
    /**
     * ID of the order that needs to be deleted
     */
    private final Integer orderId;

    private DeleteRequest(
        Integer orderId
    ) {
        this.orderId = orderId;
    }

    /**
     * Retrieves value for `orderId`
     * ID of the order that needs to be deleted
     */
    @JsonProperty("orderId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getOrderId() {
        return orderId;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof DeleteRequest && equalTo((DeleteRequest) other);
    }

    private boolean equalTo(DeleteRequest other) {
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
        return "DeleteRequest{" +
               "orderId=" + orderId +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        /**
         * ID of the order that needs to be deleted
         */
        private Integer orderId = null;

        private Builder() {}

        public Builder from(DeleteRequest other) {
            orderId(other.getOrderId());
            return this;
        }

        /**
         * Sets value for `orderId`
         * ID of the order that needs to be deleted
         */
        @JsonSetter("orderId")
        public Builder orderId(Integer orderId) {
            if (orderId == null) {
                throw new IllegalArgumentException("orderId cannot be null");
            }

            this.orderId = orderId;

            return this;
        }



        public DeleteRequest build() {
            if (orderId == null) {
                throw new IllegalStateException("orderId is required");
            }

            return new DeleteRequest(
                       orderId
                   );
        }
    }

}



