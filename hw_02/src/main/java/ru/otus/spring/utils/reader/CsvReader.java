package ru.otus.spring.utils.reader;

import ru.otus.spring.model.Exam;

public interface CsvReader {
    Exam getAsExam(int passBorder) throws Exception;
}
