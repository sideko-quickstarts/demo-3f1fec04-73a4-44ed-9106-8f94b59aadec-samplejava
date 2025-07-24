
package com.yourorg.samplejava.model;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * Tag
 */
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@JsonDeserialize(builder = Tag.Builder.class)
public final class Tag {
    private final Optional<Integer> id;
    private final Optional<String> name;

    private Tag(
        Optional<Integer> id,
        Optional<String> name
    ) {
        this.id = id;
        this.name = name;
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
        return name.orElse(null);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        return other instanceof Tag && equalTo((Tag) other);
    }

    private boolean equalTo(Tag other) {
        return
            id.equals(other.id) &&

            name.equals(other.name)
            ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                   this.id,
                   this.name
               );
    }

    @Override
    public String toString() {
        return "Tag{" +
               "id=" + id +
               "name=" + name +
               '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Optional<Integer> id = Optional.empty();
        private Optional<String> name = Optional.empty();

        private Builder() {}

        public Builder from(Tag other) {
            id(other.getId());
            name(other.getName());
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
            this.name = Optional.ofNullable(name);

            return this;
        }

        public Builder name(Optional<String> name) {
            this.name = name;
            return this;
        }


        public Tag build() {
            return new Tag(
                       id,
                       name
                   );
        }
    }

}



