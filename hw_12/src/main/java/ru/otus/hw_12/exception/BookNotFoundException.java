package ru.otus.hw_12.exception;

public class BookNotFoundException extends RepositoryException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
