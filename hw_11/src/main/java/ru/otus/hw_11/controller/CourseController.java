package ru.otus.hw_11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw_11.model.Course;
import ru.otus.hw_11.repository.CourseRepository;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping("/courses")
    public Flux<Course> readAll() {
        return courseRepository.findAll();
    }

    @GetMapping("/courses/{id}")
    public Mono<Course> readCourse(@PathVariable("id") String id) {
        return courseRepository.findById(id);
    }

    @PostMapping("/courses")
    public Mono<Course> createCourse(@RequestBody Course student) {
        return courseRepository.save(student);
    }

    @PutMapping("/courses/{id}")
    public Mono<Course> update(@RequestBody Course student) {
        return courseRepository.save(student);
    }

    @DeleteMapping("/courses/{id}")
    public Mono<Void> deleteCourse(@PathVariable("id") String id) {
        return courseRepository.deleteById(id);
    }
}
