package ru.otus.hw_06.repository;

import ru.otus.hw_06.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    List<Author> findAll();

    Optional<Author> findByName(String name);

    void deleteById(long id);

    Author findById(long id);
}
