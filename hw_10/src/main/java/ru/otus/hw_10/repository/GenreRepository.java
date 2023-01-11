package ru.otus.hw_10.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_10.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findByName(String name);
}
