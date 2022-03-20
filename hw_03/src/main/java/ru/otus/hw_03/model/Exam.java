package ru.otus.hw_03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Exam {
    private List<Line> lines;
    private int passBorder;
}
