package ru.otus.hw_03.exceptions;

import java.util.Objects;

public class ExamPrinterException extends RuntimeException {
    protected ExamPrinterException(final String message) {
        super(Objects.requireNonNull(message));
    }
}
