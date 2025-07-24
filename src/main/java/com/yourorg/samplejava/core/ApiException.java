package com.yourorg.samplejava.core;

import okhttp3.Response;

/**
 * Exception thrown for API-related errors.
 */
public class ApiException extends RuntimeException {

    private final Response response;

    /**
     * Constructs a new ApiException with the specified detail message.
     *
     * @param message the detail message
     */
    public ApiException(String message) {
        super(message);
        this.response = null;
    }

    /**
     * Constructs a new ApiException with the specified detail message and
     * cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.response = null;
    }

    /**
     * Constructs a new ApiException with the specified detail message and HTTP
     * Response.
     *
     * @param message the detail message
     * @param res the HTTP response
     */
    public ApiException(String message, Response res) {
        super(message);
        this.response = res;
    }

    /**
     * Constructs a new ApiException with the specified detail message, cause,
     * and HTTP response.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     * @param res the HTTP response
     */
    public ApiException(String message, Throwable cause, Response res) {
        super(message, cause);
        this.response = res;
    }

    /**
     * Returns the HTTP response associated with this exception if present.
     *
     * @return the HTTP response or null
     */
    public Response getResponse() {
        return this.response;
    }

}
