
package com.yourorg.samplejava.model;

import com.fasterxml.jackson.annotation.JsonValue;




/**
 * Status values that need to be considered for filter
 */
public enum PetFindByStatusStatusEnum {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;

    PetFindByStatusStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}


