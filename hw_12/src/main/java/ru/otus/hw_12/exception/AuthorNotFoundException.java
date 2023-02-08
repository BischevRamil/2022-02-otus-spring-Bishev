package ru.otus.hw_12.exception;

public class AuthorNotFoundException extends RepositoryException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
