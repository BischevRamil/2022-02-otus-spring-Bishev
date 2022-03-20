package ru.otus.spring.utils.reader;

public class NoCorrectAnswerException extends CsvReaderException {
    protected NoCorrectAnswerException(String message) {
        super(message);
    }
}
