package ru.otus.hw_09.repository.impl;


import ru.otus.hw_09.model.Genre;

import java.util.Optional;

public interface GenreCustomizeRepository<T, ID> {

    Optional<Genre> findByName(String name);

    <S extends T> S save(S entity);

}
