package ru.otus.hw_06.repository.jdbc.exception;

public class GenreNotFoundException extends RepositoryException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
