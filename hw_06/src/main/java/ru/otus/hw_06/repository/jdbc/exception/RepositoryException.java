package ru.otus.hw_06.repository.jdbc.exception;

import java.util.Objects;

public class RepositoryException extends RuntimeException {
    protected RepositoryException(final String message) {
        super(Objects.requireNonNull(message), null, false, false);
    }
}
