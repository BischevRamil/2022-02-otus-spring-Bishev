package ru.otus.hw_09.exception;

public class DuplicateAuthorBookException extends RepositoryException {
    public DuplicateAuthorBookException(String message) {
        super(message);
    }
}
