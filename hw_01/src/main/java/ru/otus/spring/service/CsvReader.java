package ru.otus.spring.service;

import ru.otus.spring.model.Exam;

public interface CsvReader {
    Exam getAsExam() throws Exception;
}
