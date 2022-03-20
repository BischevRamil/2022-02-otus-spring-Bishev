package ru.otus.hw_03.exceptions;

public class NoAnswersException extends CsvReaderException {
    public NoAnswersException(String message) {
        super(message);
    }
}
