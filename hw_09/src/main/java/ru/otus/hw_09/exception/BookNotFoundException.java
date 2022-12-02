package ru.otus.hw_09.exception;

public class BookNotFoundException extends RepositoryException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
