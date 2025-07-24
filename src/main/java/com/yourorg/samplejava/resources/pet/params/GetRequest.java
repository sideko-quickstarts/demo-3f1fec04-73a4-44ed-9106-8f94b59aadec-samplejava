
package com.yourorg.samplejava.resources.pet.params;

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
     * ID of pet to return
     */
    private final Integer petId;

    private GetRequest(
        Integer petId
    ) {
        this.petId = petId;
    }

    /**
     * Retrieves value for `petId`
     * ID of pet to return
     */
    @JsonProperty("petId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getPetId() {
        return petId;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof GetRequest && equalTo((GetRequest) other);
    }

    private boolean equalTo(GetRequest other) {
        return
            petId.equals(other.petId)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.petId
               );
    }

    @Override
    public String toString() {
        return "GetRequest{" +
               "petId=" + petId +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        /**
         * ID of pet to return
         */
        private Integer petId = null;

        private Builder() {}

        public Builder from(GetRequest other) {
            petId(other.getPetId());
            return this;
        }

        /**
         * Sets value for `petId`
         * ID of pet to return
         */
        @JsonSetter("petId")
        public Builder petId(Integer petId) {
            if (petId == null) {
                throw new IllegalArgumentException("petId cannot be null");
            }

            this.petId = petId;

            return this;
        }



        public GetRequest build() {
            if (petId == null) {
                throw new IllegalStateException("petId is required");
            }

            return new GetRequest(
                       petId
                   );
        }
    }

}



