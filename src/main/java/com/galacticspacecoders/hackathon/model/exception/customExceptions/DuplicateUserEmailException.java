package com.galacticspacecoders.hackathon.model.exception.customExceptions;

public class DuplicateUserEmailException extends RuntimeException{
    public DuplicateUserEmailException() {
        super("Duplicate user email");
    }
    public DuplicateUserEmailException(String message) {
        super(message);
    }
}
