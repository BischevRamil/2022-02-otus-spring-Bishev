package ru.otus.hw_09.exception;

public class DuplicateAuthorNameException extends RepositoryException {
    public DuplicateAuthorNameException(String message) {
        super(message);
    }
}
