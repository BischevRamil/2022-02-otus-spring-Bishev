package ru.otus.hw_05.repository;

import ru.otus.hw_05.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    void add(Author author);

    List<Author> findAll();

    Optional<Author> findByName(String name);

}
