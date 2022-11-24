package ru.otus.hw_07.exception;

public class CommentNotFoundException extends RepositoryException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}
