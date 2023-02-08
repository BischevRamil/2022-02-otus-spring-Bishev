package ru.otus.hw_12.exception;

import java.util.Objects;

public class RepositoryException extends RuntimeException {
    protected RepositoryException(final String message) {
        super(Objects.requireNonNull(message), null, false, false);
    }
}
