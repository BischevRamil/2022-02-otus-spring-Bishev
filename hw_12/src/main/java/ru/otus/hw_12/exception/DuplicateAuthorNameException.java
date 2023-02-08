package ru.otus.hw_12.exception;

public class DuplicateAuthorNameException extends RepositoryException {
    public DuplicateAuthorNameException(String message) {
        super(message);
    }
}
