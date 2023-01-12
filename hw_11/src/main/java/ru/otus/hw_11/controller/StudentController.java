package ru.otus.hw_11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw_11.model.Student;
import ru.otus.hw_11.repository.StudentRepository;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public Flux<Student> readAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Mono<Student> readStudent(@PathVariable("id") String id) {
        return studentRepository.findById(id);
    }

    @PostMapping("/students")
    public Mono<Student> createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public Mono<Student> update(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{id}")
    public Mono<Void> deleteStudent(@PathVariable("id") String id) {
        return studentRepository.deleteById(id);
    }

}
