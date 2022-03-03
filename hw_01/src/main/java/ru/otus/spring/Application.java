package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.CsvReader;
import ru.otus.spring.service.ExamPrinter;

public class Application {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        final CsvReader csvReader = context.getBean(CsvReader.class);
        final ExamPrinter examPrinter = context.getBean(ExamPrinter.class);
        examPrinter.print(csvReader.getAsExam());
    }
}
