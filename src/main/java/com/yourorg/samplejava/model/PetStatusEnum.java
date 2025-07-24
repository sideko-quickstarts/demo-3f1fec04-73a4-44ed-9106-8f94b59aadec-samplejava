
package com.yourorg.samplejava.model;

import com.fasterxml.jackson.annotation.JsonValue;




/**
 * pet status in the store
 */
public enum PetStatusEnum {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private final String value;

    PetStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}


