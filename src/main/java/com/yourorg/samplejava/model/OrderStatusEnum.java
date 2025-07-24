
package com.yourorg.samplejava.model;

import com.fasterxml.jackson.annotation.JsonValue;




/**
 * Order Status
 */
public enum OrderStatusEnum {
    APPROVED("approved"),
    DELIVERED("delivered"),
    PLACED("placed");

    private final String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    @java.lang.Override
    public String toString() {
        return this.value;
    }
}


