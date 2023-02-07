package ru.otus.hw_10.exception;

public class AuthorNotFoundException extends RepositoryException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
