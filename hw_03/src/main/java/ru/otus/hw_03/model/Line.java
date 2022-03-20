package ru.otus.hw_03.model;

import lombok.Data;

import java.util.List;

@Data
public class Line {
    private final String question;
    private final List<Answer> answers;
}
