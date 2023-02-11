package ru.otus.hw_12.exception;

public class DuplicateAuthorBookException extends RepositoryException {
    public DuplicateAuthorBookException(String message) {
        super(message);
    }
}
