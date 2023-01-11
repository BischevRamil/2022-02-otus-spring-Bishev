package ru.otus.hw_10.service;


import ru.otus.hw_10.exception.AuthorNotFoundException;
import ru.otus.hw_10.exception.BookNotFoundException;
import ru.otus.hw_10.exception.DuplicateAuthorBookException;
import ru.otus.hw_10.model.Author;
import ru.otus.hw_10.model.Book;

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
