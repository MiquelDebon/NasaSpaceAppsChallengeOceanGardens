package com.galacticspacecoders.hackathon.model.exception.customExceptions;

public class DuplicateUserNameException extends RuntimeException{
    public DuplicateUserNameException() {
        super("Duplicate user name");
    }
    public DuplicateUserNameException(String message) {
        super(message);
    }
}
