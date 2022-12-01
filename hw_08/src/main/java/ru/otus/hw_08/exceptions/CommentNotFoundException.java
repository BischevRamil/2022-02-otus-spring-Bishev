package ru.otus.hw_08.exceptions;

public class CommentNotFoundException extends RepositoryException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
