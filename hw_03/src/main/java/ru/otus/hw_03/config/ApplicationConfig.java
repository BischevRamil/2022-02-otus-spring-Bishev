package ru.otus.hw_03.config;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Configuration
public class ApplicationConfig {

    private final File file;

    public ApplicationConfig(@Value("${application.fileName}_${application.locale}.csv") String fileName) throws FileNotFoundException {
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
        return new CSVReaderBuilder(new FileReader(file)).withCSVParser(new CSVParserBuilder().withSeparator('\t').build()).withSkipLines(1).build();
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Bean
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
