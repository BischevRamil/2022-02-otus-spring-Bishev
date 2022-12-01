package ru.otus.hw_08.repositories.impl;

import ru.otus.hw_08.models.Author;

import java.util.Optional;

public interface AuthorCustomizeRepository <T, ID> {

    Optional<Author> findByName(String name);

    <S extends T> S save(S entity);
}
