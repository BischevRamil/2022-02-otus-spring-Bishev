package ru.otus.hw_12.service;


import ru.otus.hw_12.exception.AuthorNotFoundException;
import ru.otus.hw_12.exception.DuplicateAuthorNameException;
import ru.otus.hw_12.model.Author;
import ru.otus.hw_12.model.Book;

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
