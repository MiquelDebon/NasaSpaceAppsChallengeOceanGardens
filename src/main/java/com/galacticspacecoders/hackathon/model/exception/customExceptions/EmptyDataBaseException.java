package com.galacticspacecoders.hackathon.model.exception.customExceptions;

public class EmptyDataBaseException extends RuntimeException{
    public EmptyDataBaseException() {
        super("Empty database");
    }
    public EmptyDataBaseException(String message) {
        super(message);
    }
}
