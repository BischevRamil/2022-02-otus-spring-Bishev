package ru.otus.hw_10.exception;

public class DuplicateAuthorNameException extends RepositoryException {
    public DuplicateAuthorNameException(String message) {
        super(message);
    }
}
