package ru.unibell.contactservice.exception;

public class InvalidContactTypeException extends RuntimeException {
    public InvalidContactTypeException(String type) {
        super("Invalid contact type: " + type);
    }
}