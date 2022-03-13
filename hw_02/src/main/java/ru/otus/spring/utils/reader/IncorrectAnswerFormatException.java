package ru.otus.spring.utils.reader;

public class IncorrectAnswerFormatException extends CsvReaderException {
    protected IncorrectAnswerFormatException(String message) {
        super(message);
    }
}
