package ru.otus.hw_10.service;


import ru.otus.hw_10.exception.AuthorNotFoundException;
import ru.otus.hw_10.exception.DuplicateAuthorNameException;
import ru.otus.hw_10.model.Author;
import ru.otus.hw_10.model.Book;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(String id) throws AuthorNotFoundException;

    void deleteById(String id) throws AuthorNotFoundException;

    void delete(Author author) throws AuthorNotFoundException;

    Author findByName(String name) throws AuthorNotFoundException;

    Author save(Author author) throws DuplicateAuthorNameException;

    Author findAuthorByBook(Book book) throws AuthorNotFoundException;
}
