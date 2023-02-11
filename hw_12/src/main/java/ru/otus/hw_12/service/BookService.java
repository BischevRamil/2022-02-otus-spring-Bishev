package ru.otus.hw_12.service;


import ru.otus.hw_12.exception.AuthorNotFoundException;
import ru.otus.hw_12.exception.BookNotFoundException;
import ru.otus.hw_12.exception.DuplicateAuthorBookException;
import ru.otus.hw_12.model.Author;
import ru.otus.hw_12.model.Book;

import java.util.List;

public interface BookService {

    long count();

    Book findByTitle(String name) throws BookNotFoundException;

    Book findById(String id) throws BookNotFoundException;

    void deleteById(String id);

    void deleteAll(List<Book> books);

    List<Book> findAll();

    List<Book> findAllByAuthor(Author author) throws AuthorNotFoundException;

    Book findBookByAuthorAndTitle(Author author, String title) throws BookNotFoundException;

    Book save(Book book) throws DuplicateAuthorBookException;

}
