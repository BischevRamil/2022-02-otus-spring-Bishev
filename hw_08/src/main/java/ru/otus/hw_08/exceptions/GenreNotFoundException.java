package ru.otus.hw_08.exceptions;

public class GenreNotFoundException extends RepositoryException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
