package ru.otus.spring.config;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Configuration
public class ApplicationConfig {

    private final File file;

    public ApplicationConfig(@Value("${fileName}") String fileName) throws FileNotFoundException {
        file = ResourceUtils.getFile("classpath:" + fileName);
    }

    @Bean
    public FileReader fileReader() throws FileNotFoundException {
        return new FileReader(file);
    }

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public CSVReader csvReader() throws FileNotFoundException {
        return new CSVReaderBuilder(new FileReader(file)).withSkipLines(1).build();
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }
}
