package com.magneto.service.user;

/**
 * Created by vazgent on 3/14/2017.
 */
public class UserAlreadyExistsException extends Exception {
    UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    UserAlreadyExistsException(String message) {
        super(message);
    }
}