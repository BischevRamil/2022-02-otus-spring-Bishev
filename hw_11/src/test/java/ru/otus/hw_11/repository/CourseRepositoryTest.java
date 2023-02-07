package ru.otus.hw_11.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.hw_11.Constants;
import ru.otus.hw_11.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("ru.otus.repository")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void shouldFindAllCourses() {
        StepVerifier.create(repository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void shouldReturnAllCourses() {
        Flux<Course> courseFlux = repository.findAll();

        StepVerifier
                .create(courseFlux)
                .recordWith(ArrayList::new)
                .expectNextCount(3)
                .consumeRecordedWith(results -> {
                    assertThat(results)
                            .extracting(Course::getName)
                            .contains(Constants.COURSE_1, Constants.COURSE_2, Constants.COURSE_3);
                })
                .verifyComplete();
    }

    @Test
    @DirtiesContext
    void shouldSetId() {
        Mono<Course> courseMono = repository.save(new Course("c++", LocalDate.of(2021, 1, 1)));

        StepVerifier
                .create(courseMono)
                .assertNext(course -> assertNotNull(course.getId()))
                .expectComplete()
                .verify();
    }


    @Test
    void shouldFindCorrectCourseById() {
        Mono<Course> monoCourse = repository.findById("1");

        StepVerifier.create(monoCourse)
                .assertNext((course -> assertEquals(course.getName(), Constants.COURSE_1)))
                .expectComplete()
                .verify();

    }
}
