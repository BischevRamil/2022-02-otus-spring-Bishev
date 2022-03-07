package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.utils.ExamPrinterImpl;
import ru.otus.spring.utils.printer.ExamPrinter;
import ru.otus.spring.utils.reader.CsvReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ExamPrinterConfig {

    private final CsvReader csvReader;
    private final Integer passBorder;

    public ExamPrinterConfig(CsvReader csvReader, @Value("${passBorder}") Integer passBorder) {
        checkBorderValue(passBorder);
        this.csvReader = csvReader;
        this.passBorder = passBorder;
    }

    @Bean
    public ExamPrinter examPrinter() throws Exception {
        return new ExamPrinterImpl(csvReader.getAsExam(passBorder), new BufferedReader(new InputStreamReader(System.in)), System.out);
    }

    private void checkBorderValue(Integer passBorder) {
        if (!((passBorder >= 0) && (passBorder <= 100))) {
            throw new RuntimeException("Incorrect border value. Value should be: 0 >= value <= 100");
        }
    }
}
