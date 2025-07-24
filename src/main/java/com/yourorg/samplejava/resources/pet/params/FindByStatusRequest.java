
package com.yourorg.samplejava.resources.pet.params;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yourorg.samplejava.model.PetFindByStatusStatusEnum;


/**
 * FindByStatusRequest
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = FindByStatusRequest.Builder.class)
public final class FindByStatusRequest {
    /**
     * Status values that need to be considered for filter
     */
    private final Optional<PetFindByStatusStatusEnum> status;

    private FindByStatusRequest(
        Optional<PetFindByStatusStatusEnum> status
    ) {
        this.status = status;
    }

    /**
     * Retrieves value for `status`
     * Status values that need to be considered for filter
     */
    @JsonProperty("status")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public PetFindByStatusStatusEnum getStatus() {
        return status.orElse(null);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof FindByStatusRequest && equalTo((FindByStatusRequest) other);
    }

    private boolean equalTo(FindByStatusRequest other) {
        return
            status.equals(other.status)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.status
               );
    }

    @Override
    public String toString() {
        return "FindByStatusRequest{" +
               "status=" + status +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        /**
         * Status values that need to be considered for filter
         */
        private Optional<PetFindByStatusStatusEnum> status = Optional.empty();

        private Builder() {}

        public Builder from(FindByStatusRequest other) {
            status(other.getStatus());
            return this;
        }

        /**
         * Sets value for `status`
         * Status values that need to be considered for filter
         */
        @JsonSetter("status")
        public Builder status(PetFindByStatusStatusEnum status) {
            this.status = Optional.ofNullable(status);

            return this;
        }

        public Builder status(Optional<PetFindByStatusStatusEnum> status) {
            this.status = status;
            return this;
        }


        public FindByStatusRequest build() {
            return new FindByStatusRequest(
                       status
                   );
        }
    }

}



