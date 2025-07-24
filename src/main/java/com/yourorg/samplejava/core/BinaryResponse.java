package com.yourorg.samplejava.core;

public class BinaryResponse {
    private final byte[] content;

    public BinaryResponse(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}