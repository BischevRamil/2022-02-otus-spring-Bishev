package ru.otus.hw_06.repository;

import ru.otus.hw_06.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    long count();

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);
}
