package ru.otus.hw_03.service.printer;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw_03.config.YamlProps;
import ru.otus.hw_03.exceptions.IncorrectBorderValueException;
import ru.otus.hw_03.exceptions.NotFinishedExamException;
import ru.otus.hw_03.model.Answer;
import ru.otus.hw_03.model.Exam;
import ru.otus.hw_03.model.Line;
import ru.otus.hw_03.service.reader.CsvReader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamPrinterImpl implements ExamPrinter {
    private final PrintStream out;
    private final LineReader lineReader;
    private final CsvReader csvReader;
    private final MessageSource messageSource;
    private final YamlProps props;

    @Value("${application.passBorder}")
    private int passBorder;
    private int correctAnswerCounter;
    private boolean examResult;
    private boolean isExamFinished;


    @PostConstruct
    private void postConstruct() {
        checkBorderValue(passBorder);
    }

    /**
     * Prints out data from Exam object.
     */
    @Override
    public void print() throws IOException, CsvException {
        Exam exam;
        exam = csvReader.getAsExam(passBorder);
        out.println(getMessageFromProps("msg.start.welcome"));
        out.println(getMessageFromProps("msg.start.firstName") + ":");
        String firstName;
        firstName = lineReader.readLine();
        out.println(getMessageFromProps("msg.start.lastName") + ":");
        String lastName;
        lastName = lineReader.readLine();
        out.println(getMessageFromProps("msg.start.greeting") + ", " + firstName + " " + lastName);
        exam.getLines().forEach(this::accept);
        final int userResult = getPercentageOfCompletion(exam);
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
        out.println(firstName.toUpperCase() + " " + lastName.toUpperCase() + ", " + getMessageFromProps("msg.finish.result") + " : " + userResult + "%");
        out.println(getMessageFromProps("msg.finish.result.border") + ": ".toUpperCase() + passBorder + "%");
        if (getExamResult()) {
            out.println(getMessageFromProps("msg.finish.result.passed"));
        } else {
            out.println(getMessageFromProps("msg.finish.result.failed") + "...");
        }
    }

    private void setExamResult(int userResult) {
        examResult = userResult >= passBorder;
    }

    private int getPercentageOfCompletion(Exam exam) {
        return correctAnswerCounter * 100 / exam.getLines().size();
    }


    private void proceedUserAnswerLetter(Character correctAnswerLetter) throws IOException {
        out.println("Enter the correct answer letter A B C or D ...");
        String userAnswer = lineReader.readLine();
        if (userAnswer.length() > 0) {
            if (correctAnswerLetter.equals(userAnswer.toUpperCase().charAt(0))) {
                out.println(getMessageFromProps("msg.inprogress.answer.positiveresult"));
                correctAnswerCounter++;
            } else {
                out.println(getMessageFromProps("msg.inprogress.answer.negativeresult"));
            }
        } else {
            out.println(getMessageFromProps("msg.inprogress.answer.empty") + "...");
        }
    }

    @SneakyThrows
    private void accept(Line l) {
        out.println(l.getQuestion());
        final List<Answer> answers = l.getAnswers();
        answers.forEach(answer -> {
            out.println(answer.getText());
        });
        final Optional<Answer> first = answers.stream().filter(Answer::isCorrectAnswer).findFirst();
        if (first.isEmpty()) {
            throw new IOException("There is no correct answers in csv file. " +
                    "Correct answer should be mark with '+' at the end");
        }
        proceedUserAnswerLetter(first.get().getAnswerOptionLetter());
    }

    private void checkBorderValue(Integer passBorder) {
        if (!((passBorder >= 0) && (passBorder <= 100))) {
            throw new IncorrectBorderValueException("Incorrect border value. Value should be: 0 >= value <= 100");
        }
    }

    private String getMessageFromProps(String s) {
        return messageSource.getMessage(s, null, props.getLocale()).toUpperCase();
    }
}
