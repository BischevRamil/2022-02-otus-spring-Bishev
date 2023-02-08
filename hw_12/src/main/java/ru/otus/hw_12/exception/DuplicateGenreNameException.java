package ru.otus.hw_12.exception;

public class DuplicateGenreNameException extends RepositoryException {
    public DuplicateGenreNameException(String message) {
        super(message);
    }
}
