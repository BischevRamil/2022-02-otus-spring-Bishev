package ru.otus.hw_09.repository.impl;


import ru.otus.hw_09.model.Author;
import ru.otus.hw_09.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookCustomizeRepository<T, ID> {

    List<Book> findAllByAuthor(Author author);

    Optional<Book> findBookByAuthorAndTitle(Author author, String title);

    <S extends T> S save(S entity);

}
