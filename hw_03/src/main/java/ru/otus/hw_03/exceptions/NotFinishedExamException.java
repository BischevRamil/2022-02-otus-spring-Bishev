package ru.otus.hw_03.exceptions;

public class NotFinishedExamException extends ExamPrinterException {
    public NotFinishedExamException(String message) {
        super(message);
    }
}
