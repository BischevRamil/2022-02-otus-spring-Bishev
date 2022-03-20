package ru.otus.hw_03.exceptions;

import java.util.Objects;

public class CsvReaderException extends RuntimeException {
    protected CsvReaderException(final String message) {
        super(Objects.requireNonNull(message));
    }
}
