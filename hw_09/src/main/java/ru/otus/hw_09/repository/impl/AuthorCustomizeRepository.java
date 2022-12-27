package ru.otus.hw_09.repository.impl;


import ru.otus.hw_09.model.Author;
import ru.otus.hw_09.model.Book;

import java.util.Optional;

public interface AuthorCustomizeRepository<T, ID> {

    Optional<Author> findByName(String name);

    <S extends T> S save(S entity);

    Author findAuthorByBook(Book book);

}
