package ru.otus.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.config.YamlProps;
import ru.otus.spring.utils.printer.ExamPrinter;

@SpringBootApplication
@EnableConfigurationProperties(YamlProps.class)
public class Hw03Application implements CommandLineRunner {

    private final ExamPrinter examPrinter;

    public Hw03Application(ExamPrinter examPrinter) {
        this.examPrinter = examPrinter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Hw03Application.class, args);
    }

    @Override
    public void run(String... args) {
        examPrinter.print();
    }
}
