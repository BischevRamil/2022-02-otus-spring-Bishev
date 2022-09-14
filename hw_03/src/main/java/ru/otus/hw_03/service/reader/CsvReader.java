package ru.otus.hw_03.service.reader;

import com.opencsv.exceptions.CsvException;
import ru.otus.hw_03.model.Exam;

import java.io.IOException;

public interface CsvReader {
    Exam getAsExam(int passBorder) throws IOException, CsvException;
}
