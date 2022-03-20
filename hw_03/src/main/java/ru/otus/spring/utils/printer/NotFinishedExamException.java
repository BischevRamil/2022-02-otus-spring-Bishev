package ru.otus.spring.utils.printer;

public class NotFinishedExamException extends ExamPrinterException {
    public NotFinishedExamException(String message) {
        super(message);
    }
}
