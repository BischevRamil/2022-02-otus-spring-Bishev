package ru.otus.spring.config;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import ru.otus.spring.utils.reader.CsvReader;
import ru.otus.spring.utils.reader.CsvReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Configuration
public class CsvReaderConfig {

    private final File file;

    public CsvReaderConfig(@Value("${fileName}") String fileName) throws FileNotFoundException {
        file = ResourceUtils.getFile("classpath:" + fileName);
    }

    @Bean
    public CsvReader csvReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        return new CsvReaderImpl(csvReader, fileReader);
    }
}
