package ru.otus.hw_06.repository.jdbc.exception;

public class CommentNotFoundException extends RepositoryException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}
