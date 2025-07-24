
package com.yourorg.samplejava.resources.pet.params;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yourorg.samplejava.model.Category;
import com.yourorg.samplejava.model.PetStatusEnum;
import com.yourorg.samplejava.model.Tag;


/**
 * CreateRequest
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = CreateRequest.Builder.class)
public final class CreateRequest {
    private final Optional<Category> category;
    private final Optional<Integer> id;
    private final String name;
    private final List<String> photoUrls;
    /**
     * pet status in the store
     */
    private final Optional<PetStatusEnum> status;
    private final Optional<List<Tag>> tags;

    private CreateRequest(
        Optional<Category> category,
        Optional<Integer> id,
        String name,
        List<String> photoUrls,
        Optional<PetStatusEnum> status,
        Optional<List<Tag>> tags
    ) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.photoUrls = photoUrls;
        this.status = status;
        this.tags = tags;
    }

    /**
     * Retrieves value for `category`
     */
    @JsonProperty("category")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public Category getCategory() {
        return category.orElse(null);
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
     * Retrieves value for `name`
     */
    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public String getName() {
        return name;
    }

    /**
     * Retrieves value for `photoUrls`
     */
    @JsonProperty("photoUrls")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    /**
     * Retrieves value for `status`
     * pet status in the store
     */
    @JsonProperty("status")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public PetStatusEnum getStatus() {
        return status.orElse(null);
    }

    /**
     * Retrieves value for `tags`
     */
    @JsonProperty("tags")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public List<Tag> getTags() {
        return tags.orElse(null);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof CreateRequest && equalTo((CreateRequest) other);
    }

    private boolean equalTo(CreateRequest other) {
        return
            category.equals(other.category) &&

            id.equals(other.id) &&

            name.equals(other.name) &&

            photoUrls.equals(other.photoUrls) &&

            status.equals(other.status) &&

            tags.equals(other.tags)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.category,
                   this.id,
                   this.name,
                   this.photoUrls,
                   this.status,
                   this.tags
               );
    }

    @Override
    public String toString() {
        return "CreateRequest{" +
               "category=" + category +
               "id=" + id +
               "name=" + name +
               "photoUrls=" + photoUrls +
               "status=" + status +
               "tags=" + tags +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Optional<Category> category = Optional.empty();
        private Optional<Integer> id = Optional.empty();
        private String name = null;
        private List<String> photoUrls = null;
        /**
         * pet status in the store
         */
        private Optional<PetStatusEnum> status = Optional.empty();
        private Optional<List<Tag>> tags = Optional.empty();

        private Builder() {}

        public Builder from(CreateRequest other) {
            category(other.getCategory());
            id(other.getId());
            name(other.getName());
            photoUrls(other.getPhotoUrls());
            status(other.getStatus());
            tags(other.getTags());
            return this;
        }

        /**
         * Sets value for `category`
         */
        @JsonSetter("category")
        public Builder category(Category category) {
            this.category = Optional.ofNullable(category);

            return this;
        }

        public Builder category(Optional<Category> category) {
            this.category = category;
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
         * Sets value for `name`
         */
        @JsonSetter("name")
        public Builder name(String name) {
            if (name == null) {
                throw new IllegalArgumentException("name cannot be null");
            }

            this.name = name;

            return this;
        }


        /**
         * Sets value for `photoUrls`
         */
        @JsonSetter("photoUrls")
        public Builder photoUrls(List<String> photoUrls) {
            if (photoUrls == null) {
                throw new IllegalArgumentException("photoUrls cannot be null");
            }

            this.photoUrls = photoUrls;

            return this;
        }


        /**
         * Sets value for `status`
         * pet status in the store
         */
        @JsonSetter("status")
        public Builder status(PetStatusEnum status) {
            this.status = Optional.ofNullable(status);

            return this;
        }

        public Builder status(Optional<PetStatusEnum> status) {
            this.status = status;
            return this;
        }

        /**
         * Sets value for `tags`
         */
        @JsonSetter("tags")
        public Builder tags(List<Tag> tags) {
            this.tags = Optional.ofNullable(tags);

            return this;
        }

        public Builder tags(Optional<List<Tag>> tags) {
            this.tags = tags;
            return this;
        }


        public CreateRequest build() {
            if (name == null) {
                throw new IllegalStateException("name is required");
            }

            if (photoUrls == null) {
                throw new IllegalStateException("photoUrls is required");
            }

            return new CreateRequest(
                       category,
                       id,
                       name,
                       photoUrls,
                       status,
                       tags
                   );
        }
    }

}



