package org.unibl.etf.onlinefitness.exceptions;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException() {
        super();
    }

    public InvalidUsernameException(String message) {
        super(message);
    }

}