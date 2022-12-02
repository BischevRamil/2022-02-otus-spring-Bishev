package ru.otus.hw_09.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_09.model.Genre;
import ru.otus.hw_09.repository.impl.GenreCustomizeRepository;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreCustomizeRepository<Genre, String> {

}
