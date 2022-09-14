package ru.otus.hw_03.exceptions;

public class NoCorrectAnswerException extends CsvReaderException {
    public NoCorrectAnswerException(String message) {
        super(message);
    }
}
