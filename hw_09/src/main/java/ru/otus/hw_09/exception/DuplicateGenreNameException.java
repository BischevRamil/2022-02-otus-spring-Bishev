package ru.otus.hw_09.exception;

public class DuplicateGenreNameException extends RepositoryException {
    public DuplicateGenreNameException(String message) {
        super(message);
    }
}
