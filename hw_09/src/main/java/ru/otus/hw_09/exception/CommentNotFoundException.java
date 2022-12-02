package ru.otus.hw_09.exception;

public class CommentNotFoundException extends RepositoryException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
