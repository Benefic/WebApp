package ru.abenefic.spring.shop.core.exceptions;

public class NoSuchPageException extends RuntimeException {
    public NoSuchPageException(String message) {
        super(message);
    }
}
