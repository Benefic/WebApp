package ru.abenefic.spring.exceptions;

public class NoSuchPageException extends RuntimeException {
    public NoSuchPageException(String message) {
        super(message);
    }
}
