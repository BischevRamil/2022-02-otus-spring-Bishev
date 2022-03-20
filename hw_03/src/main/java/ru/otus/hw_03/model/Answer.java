package ru.otus.hw_03.model;

import lombok.Data;

@Data
public class Answer {
    private final Character answerOptionLetter;
    private final String text;
    private final boolean isCorrectAnswer;
}
