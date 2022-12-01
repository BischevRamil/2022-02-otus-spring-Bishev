package ru.otus.hw_08.repositories.impl;

import ru.otus.hw_08.models.Author;
import ru.otus.hw_08.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookCustomizeRepository <T, ID> {

    List<Book> findAllByAuthor(Author author);

    Optional<Book> findBookByAuthorAndTitle(Author author, String title);
}
