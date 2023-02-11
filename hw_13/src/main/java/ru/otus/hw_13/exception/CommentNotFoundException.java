package ru.otus.hw_13.exception;

public class CommentNotFoundException extends RepositoryException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
