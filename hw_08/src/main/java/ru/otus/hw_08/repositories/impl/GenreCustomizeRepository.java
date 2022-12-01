package ru.otus.hw_08.repositories.impl;

import ru.otus.hw_08.models.Genre;

import java.util.Optional;

public interface GenreCustomizeRepository <T, ID> {

    Optional<Genre> findByName(String name);

    <S extends T> S save(S entity);
}
