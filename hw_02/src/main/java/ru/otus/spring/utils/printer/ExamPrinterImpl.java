package ru.otus.spring.utils.printer;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.model.Answer;
import ru.otus.spring.model.Exam;
import ru.otus.spring.model.Line;
import ru.otus.spring.utils.reader.CsvReader;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

@Service
public class ExamPrinterImpl implements ExamPrinter {

    private final PrintStream out;
    private final LineReader lineReader;
    private final Exam exam;

    @Value("${passBorder}")
    private int passBorder;
    private int correctAnswerCounter;
    private boolean examResult;
    private boolean isExamFinished;

    public ExamPrinterImpl(LineReader lineReader, CsvReader csvReader, PrintStream out) throws Exception {
        this.lineReader = lineReader;
        this.out = out;
        exam = csvReader.getAsExam(passBorder);
        checkBorderValue(passBorder);
    }

    /**
     * Prints out data from Exam object.
     */
    @Override
    @SneakyThrows
    public void print() {
        out.println("Welcome to java basic exam!".toUpperCase());
        out.println("Please enter firstName:");
        String firstName = lineReader.readLine();
        out.println("Please enter lastName:");
        String lastName = lineReader.readLine();
        out.println("Hello, " + firstName + " " + lastName);
        exam.getLines().forEach(this::accept);
        final int userResult = getPercentageOfCompletion();
        isExamFinished = true;
        setExamResult(userResult);
        printExamResult(firstName, lastName, userResult);
    }

    @Override
    public boolean getExamResult() {
        if (!isExamFinished) {
            throw new NotFinishedExamException("Not possible to show result of exam. Exam is not finished");
        } else {
            return examResult;
        }
    }

    private void printExamResult(String firstName, String lastName, int userResult) {
        out.println(firstName.toUpperCase() + " " + lastName.toUpperCase() +
                ", YOUR EXAM RESULT : " + userResult + "%");
        out.println("Minimum pass border: ".toUpperCase() + passBorder + "%");
        if (getExamResult()) {
            out.println("Congratulation! You pass the exam".toUpperCase());
        } else {
            out.println("You failed the exam. Try again later...".toUpperCase());
        }
    }

    private void setExamResult(int userResult) {
        examResult = userResult >= passBorder;
    }

    private int getPercentageOfCompletion() {
        return correctAnswerCounter * 100 / exam.getLines().size();
    }

    @SneakyThrows
    private void proceedUserAnswerLetter(Character correctAnswerLetter) {
        out.println("Enter the correct answer letter A B C or D ...");
        String userAnswer = lineReader.readLine();
        if (userAnswer.length() > 0) {
            if (correctAnswerLetter.equals(userAnswer.toUpperCase().charAt(0))) {
                out.println("You answer is CORRECT");
                correctAnswerCounter++;
            } else {
                out.println("Your answer is WRONG");
            }
        } else {
            out.println("Empty answer...");
        }
    }

    private void accept(Line l) {
        out.println(l.getQuestion());
        final List<Answer> answers = l.getAnswers();
        answers.forEach(answer -> {
            out.println(answer.getText());
        });
        final Optional<Answer> first = answers.stream().filter(Answer::isCorrectAnswer).findFirst();
        if (first.isEmpty()) {
            throw new RuntimeException("There is no correct answers in csv file. " +
                    "Correct answer should be mark with '+' at the end");
        }
        proceedUserAnswerLetter(first.get().getAnswerOptionLetter());
    }

    private void checkBorderValue(Integer passBorder) {
        if (!((passBorder >= 0) && (passBorder <= 100))) {
            throw new IncorrectBorderValueException("Incorrect border value. Value should be: 0 >= value <= 100");
        }
    }
}
