package ru.otus.hw_06.repository.jdbc.exception;

public class BookNotFoundException extends RepositoryException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
