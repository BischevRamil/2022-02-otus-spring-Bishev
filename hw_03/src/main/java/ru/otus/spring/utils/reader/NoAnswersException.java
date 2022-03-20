package ru.otus.spring.utils.reader;

public class NoAnswersException extends CsvReaderException {
    protected NoAnswersException(String message) {
        super(message);
    }
}
