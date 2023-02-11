package ru.otus.hw_13.exception;

public class AuthorNotFoundException extends RepositoryException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
