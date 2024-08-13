package com.company.taskmanager.models.errors;

/**
 * Represents an error response in the application.
 * <p>
 * This class encapsulates the details of an error that occurred during
 * application execution, including the HTTP status code and an error message.
 * It is used to provide a structured error response to the client.
 * </p>
 */
public class AppError {

    /**
     * HTTP status code associated with the error.
     * <p>
     * This code represents the type of error that occurred,
     * such as 404 for "Not Found"
     * or 500 for "Internal Server Error".
     * </p>
     */
    private int statusCode;

    /**
     * Error message providing details about the error.
     * <p>
     * This message gives additional context or description of
     * the error that occurred.
     * </p>
     */
    private String message;

    /**
     * Default constructor for creating an instance of {@link AppError}.
     * <p>
     * This constructor initializes the error with default values.
     * </p>
     */
    public AppError() {
    }

    /**
     * Constructs an {@link AppError} with the specified status code and message.
     *
     * @param statusCode the HTTP status code associated with the error
     * @param message the error message providing details about the error
     */
    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * Gets the HTTP status code associated with the error.
     *
     * @return the HTTP status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the HTTP status code associated with the error.
     *
     * @param statusCode the HTTP status code to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the error message providing details about the error.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message providing details about the error.
     *
     * @param message the error message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
