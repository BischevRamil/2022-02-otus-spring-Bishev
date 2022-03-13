package ru.otus.spring.model;

import lombok.*;

import java.util.List;

@Data
public class Line {
    private final String question;
    private final List<Answer> answers;

}
