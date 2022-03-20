package ru.otus.hw_03.service.printer;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface ExamPrinter {
    void print() throws IOException, CsvException;

    boolean getExamResult();
}
