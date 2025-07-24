
package com.yourorg.samplejava.resources.store.order.params;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yourorg.samplejava.model.OrderStatusEnum;


/**
 * CreateRequest
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = CreateRequest.Builder.class)
public final class CreateRequest {
    private final Optional<Boolean> complete;
    private final Optional<Integer> id;
    private final Optional<Integer> petId;
    private final Optional<Integer> quantity;
    private final Optional<String> shipDate;
    /**
     * Order Status
     */
    private final Optional<OrderStatusEnum> status;

    private CreateRequest(
        Optional<Boolean> complete,
        Optional<Integer> id,
        Optional<Integer> petId,
        Optional<Integer> quantity,
        Optional<String> shipDate,
        Optional<OrderStatusEnum> status
    ) {
        this.complete = complete;
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
    }

    /**
     * Retrieves value for `complete`
     */
    @JsonProperty("complete")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Boolean getComplete() {
        return complete.orElse(null);
    }

    /**
     * Retrieves value for `id`
     */
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getId() {
        return id.orElse(null);
    }

    /**
     * Retrieves value for `petId`
     */
    @JsonProperty("petId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getPetId() {
        return petId.orElse(null);
    }

    /**
     * Retrieves value for `quantity`
     */
    @JsonProperty("quantity")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getQuantity() {
        return quantity.orElse(null);
    }

    /**
     * Retrieves value for `shipDate`
     */
    @JsonProperty("shipDate")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public String getShipDate() {
        return shipDate.orElse(null);
    }

    /**
     * Retrieves value for `status`
     * Order Status
     */
    @JsonProperty("status")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public OrderStatusEnum getStatus() {
        return status.orElse(null);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof CreateRequest && equalTo((CreateRequest) other);
    }

    private boolean equalTo(CreateRequest other) {
        return
            complete.equals(other.complete) &&

            id.equals(other.id) &&

            petId.equals(other.petId) &&

            quantity.equals(other.quantity) &&

            shipDate.equals(other.shipDate) &&

            status.equals(other.status)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.complete,
                   this.id,
                   this.petId,
                   this.quantity,
                   this.shipDate,
                   this.status
               );
    }

    @Override
    public String toString() {
        return "CreateRequest{" +
               "complete=" + complete +
               "id=" + id +
               "petId=" + petId +
               "quantity=" + quantity +
               "shipDate=" + shipDate +
               "status=" + status +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Optional<Boolean> complete = Optional.empty();
        private Optional<Integer> id = Optional.empty();
        private Optional<Integer> petId = Optional.empty();
        private Optional<Integer> quantity = Optional.empty();
        private Optional<String> shipDate = Optional.empty();
        /**
         * Order Status
         */
        private Optional<OrderStatusEnum> status = Optional.empty();

        private Builder() {}

        public Builder from(CreateRequest other) {
            complete(other.getComplete());
            id(other.getId());
            petId(other.getPetId());
            quantity(other.getQuantity());
            shipDate(other.getShipDate());
            status(other.getStatus());
            return this;
        }

        /**
         * Sets value for `complete`
         */
        @JsonSetter("complete")
        public Builder complete(Boolean complete) {
            this.complete = Optional.ofNullable(complete);

            return this;
        }

        public Builder complete(Optional<Boolean> complete) {
            this.complete = complete;
            return this;
        }

        /**
         * Sets value for `id`
         */
        @JsonSetter("id")
        public Builder id(Integer id) {
            this.id = Optional.ofNullable(id);

            return this;
        }

        public Builder id(Optional<Integer> id) {
            this.id = id;
            return this;
        }

        /**
         * Sets value for `petId`
         */
        @JsonSetter("petId")
        public Builder petId(Integer petId) {
            this.petId = Optional.ofNullable(petId);

            return this;
        }

        public Builder petId(Optional<Integer> petId) {
            this.petId = petId;
            return this;
        }

        /**
         * Sets value for `quantity`
         */
        @JsonSetter("quantity")
        public Builder quantity(Integer quantity) {
            this.quantity = Optional.ofNullable(quantity);

            return this;
        }

        public Builder quantity(Optional<Integer> quantity) {
            this.quantity = quantity;
            return this;
        }

        /**
         * Sets value for `shipDate`
         */
        @JsonSetter("shipDate")
        public Builder shipDate(String shipDate) {
            this.shipDate = Optional.ofNullable(shipDate);

            return this;
        }

        public Builder shipDate(Optional<String> shipDate) {
            this.shipDate = shipDate;
            return this;
        }

        /**
         * Sets value for `status`
         * Order Status
         */
        @JsonSetter("status")
        public Builder status(OrderStatusEnum status) {
            this.status = Optional.ofNullable(status);

            return this;
        }

        public Builder status(Optional<OrderStatusEnum> status) {
            this.status = status;
            return this;
        }


        public CreateRequest build() {
            return new CreateRequest(
                       complete,
                       id,
                       petId,
                       quantity,
                       shipDate,
                       status
                   );
        }
    }

}



