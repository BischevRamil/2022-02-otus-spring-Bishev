package ru.otus.hw_11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.hw_11.model.Course;

public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
}
