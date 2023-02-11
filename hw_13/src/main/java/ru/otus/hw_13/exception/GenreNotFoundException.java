package ru.otus.hw_13.exception;

public class GenreNotFoundException extends RepositoryException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
