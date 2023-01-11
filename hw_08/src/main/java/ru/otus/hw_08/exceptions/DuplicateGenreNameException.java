package ru.otus.hw_08.exceptions;

public class DuplicateGenreNameException extends RepositoryException {
    public DuplicateGenreNameException(String message) {
        super(message);
    }
}
