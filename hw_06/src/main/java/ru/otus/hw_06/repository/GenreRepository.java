package ru.otus.hw_06.repository;

import ru.otus.hw_06.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    List<Genre> findAll();

    Optional<Genre> findByName(String name);

    void deleteById(long id);

    Optional<Genre> findById(long id);
}
