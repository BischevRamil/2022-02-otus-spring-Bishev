package ru.otus.hw_07.exception;

public class AuthorNotFoundException extends RepositoryException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
