package ru.otus.hw_06.repository;

import ru.otus.hw_06.model.Author;
import ru.otus.hw_06.model.Book;

import java.util.List;

public interface BookRepository {

    long count();

    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    List<Book> findAll(Author author);

    void updateTitleById(long id, String title);

    void updateCommentById(long id, String comment);

    void deleteById(long id);
}
