package ru.otus.hw_10.exception;

public class DuplicateAuthorBookException extends RepositoryException {
    public DuplicateAuthorBookException(String message) {
        super(message);
    }
}
