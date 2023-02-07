package ru.otus.hw_11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.hw_11.model.Student;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
}
