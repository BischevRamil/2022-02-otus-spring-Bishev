package ru.otus.hw_03.exceptions;

public class IncorrectAnswerFormatException extends CsvReaderException {
    public IncorrectAnswerFormatException(String message) {
        super(message);
    }
}
