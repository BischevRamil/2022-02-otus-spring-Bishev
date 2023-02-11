package ru.otus.hw_12.exception;

public class CommentNotFoundException extends RepositoryException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
