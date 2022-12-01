package ru.otus.hw_08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_08.models.Genre;
import ru.otus.hw_08.repositories.impl.GenreCustomizeRepository;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreCustomizeRepository<Genre, String> {
}
