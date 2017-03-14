package com.magneto.config;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vazgent on 3/14/2017.
 */
public class ApiError {

    /**
     * the HTTP status code
     */
    private HttpStatus status;
    /**
     * The code property is an error code specific to your particular REST API. It is usually something that conveys information very specific to your problem domain
     */
    private long code;

    /**
     * The message property is a nice human readable error message that can potentially be shown directly to an application end user (not a developer).  It should be friendly and easy to understand and convey a concise reason as to why the error occurred.  It should probaby not contain technical information.
     */
    private String message;

    /**
     * The developerMessage property conveys any and all technical information that a developer calling your REST API might find useful.  This is where you might include exception messages, stack traces, or anything else that you think will help a developer.
     */
    private String developerMessage;

    /**
     * List of constructed error messages
     */
    private List<String> errors;

    public ApiError() {
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
