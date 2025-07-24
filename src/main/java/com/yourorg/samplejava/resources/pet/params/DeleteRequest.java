
package com.yourorg.samplejava.resources.pet.params;

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
     * Pet id to delete
     */
    private final Integer petId;

    private DeleteRequest(
        Integer petId
    ) {
        this.petId = petId;
    }

    /**
     * Retrieves value for `petId`
     * Pet id to delete
     */
    @JsonProperty("petId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getPetId() {
        return petId;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof DeleteRequest && equalTo((DeleteRequest) other);
    }

    private boolean equalTo(DeleteRequest other) {
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
        return "DeleteRequest{" +
               "petId=" + petId +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        /**
         * Pet id to delete
         */
        private Integer petId = null;

        private Builder() {}

        public Builder from(DeleteRequest other) {
            petId(other.getPetId());
            return this;
        }

        /**
         * Sets value for `petId`
         * Pet id to delete
         */
        @JsonSetter("petId")
        public Builder petId(Integer petId) {
            if (petId == null) {
                throw new IllegalArgumentException("petId cannot be null");
            }

            this.petId = petId;

            return this;
        }



        public DeleteRequest build() {
            if (petId == null) {
                throw new IllegalStateException("petId is required");
            }

            return new DeleteRequest(
                       petId
                   );
        }
    }

}



