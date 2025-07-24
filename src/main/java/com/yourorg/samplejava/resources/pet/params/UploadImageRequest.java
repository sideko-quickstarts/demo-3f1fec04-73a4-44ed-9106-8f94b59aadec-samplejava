
package com.yourorg.samplejava.resources.pet.params;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * UploadImageRequest
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = UploadImageRequest.Builder.class)
public final class UploadImageRequest {
    private final java.io.File data;
    /**
     * ID of pet to update
     */
    private final Integer petId;
    /**
     * Additional Metadata
     */
    private final Optional<String> additionalMetadata;

    private UploadImageRequest(
        java.io.File data,
        Integer petId,
        Optional<String> additionalMetadata
    ) {
        this.data = data;
        this.petId = petId;
        this.additionalMetadata = additionalMetadata;
    }

    /**
     * Retrieves value for `data`
     */
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public java.io.File getData() {
        return data;
    }

    /**
     * Retrieves value for `petId`
     * ID of pet to update
     */
    @JsonProperty("petId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Integer getPetId() {
        return petId;
    }

    /**
     * Retrieves value for `additionalMetadata`
     * Additional Metadata
     */
    @JsonProperty("additionalMetadata")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public String getAdditionalMetadata() {
        return additionalMetadata.orElse(null);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof UploadImageRequest && equalTo((UploadImageRequest) other);
    }

    private boolean equalTo(UploadImageRequest other) {
        return
            data.equals(other.data) &&

            petId.equals(other.petId) &&

            additionalMetadata.equals(other.additionalMetadata)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.data,
                   this.petId,
                   this.additionalMetadata
               );
    }

    @Override
    public String toString() {
        return "UploadImageRequest{" +
               "data=" + data +
               "petId=" + petId +
               "additionalMetadata=" + additionalMetadata +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private java.io.File data = null;
        /**
         * ID of pet to update
         */
        private Integer petId = null;
        /**
         * Additional Metadata
         */
        private Optional<String> additionalMetadata = Optional.empty();

        private Builder() {}

        public Builder from(UploadImageRequest other) {
            data(other.getData());
            petId(other.getPetId());
            additionalMetadata(other.getAdditionalMetadata());
            return this;
        }

        /**
         * Sets value for `data`
         */
        @JsonSetter("data")
        public Builder data(java.io.File data) {
            if (data == null) {
                throw new IllegalArgumentException("data cannot be null");
            }

            this.data = data;

            return this;
        }


        /**
         * Sets value for `petId`
         * ID of pet to update
         */
        @JsonSetter("petId")
        public Builder petId(Integer petId) {
            if (petId == null) {
                throw new IllegalArgumentException("petId cannot be null");
            }

            this.petId = petId;

            return this;
        }


        /**
         * Sets value for `additionalMetadata`
         * Additional Metadata
         */
        @JsonSetter("additionalMetadata")
        public Builder additionalMetadata(String additionalMetadata) {
            this.additionalMetadata = Optional.ofNullable(additionalMetadata);

            return this;
        }

        public Builder additionalMetadata(Optional<String> additionalMetadata) {
            this.additionalMetadata = additionalMetadata;
            return this;
        }


        public UploadImageRequest build() {
            if (data == null) {
                throw new IllegalStateException("data is required");
            }

            if (petId == null) {
                throw new IllegalStateException("petId is required");
            }

            return new UploadImageRequest(
                       data,
                       petId,
                       additionalMetadata
                   );
        }
    }

}



