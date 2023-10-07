package com.galacticspacecoders.hackathon.model.exception.customExceptions;

public class PhytoplanktonNotFoundException extends RuntimeException {
    public PhytoplanktonNotFoundException() {
        super("Phyoplankton not found");
    }
    public PhytoplanktonNotFoundException(String message) {
        super(message);
    }
}
