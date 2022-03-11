package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.utils.printer.ExamPrinter;
import ru.otus.spring.utils.printer.ExamPrinterImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        final ExamPrinter examPrinter = context.getBean(ExamPrinterImpl.class);
        examPrinter.print();
    }
}
