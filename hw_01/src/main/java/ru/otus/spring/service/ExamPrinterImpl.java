package ru.otus.spring.service;

import ru.otus.spring.model.Exam;

public class ExamPrinterImpl implements ExamPrinter {
    @Override
    /**
     * Prints out data from Exam object
     * @param exam Exam object for print
     */
    public void print(Exam exam) {
        exam.getLines().forEach(l -> {
            System.out.println(l.getQuestion());
            l.getAnswers().forEach(System.out::println);
        });
    }
}
