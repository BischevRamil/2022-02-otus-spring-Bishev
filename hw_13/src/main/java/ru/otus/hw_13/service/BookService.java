package ru.otus.hw_13.service;


import ru.otus.hw_13.exception.AuthorNotFoundException;
import ru.otus.hw_13.exception.BookNotFoundException;
import ru.otus.hw_13.exception.DuplicateAuthorBookException;
import ru.otus.hw_13.model.Author;
import ru.otus.hw_13.model.Book;

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
