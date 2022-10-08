package ru.otus.hw_05.repository;

import ru.otus.hw_05.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    void add(Genre genre);

    List<Genre> findAll();

    Optional<Genre> findByName(String name);
}
