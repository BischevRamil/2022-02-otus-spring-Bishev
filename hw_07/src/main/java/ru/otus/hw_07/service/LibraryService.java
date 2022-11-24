package ru.otus.hw_07.service;

import ru.otus.hw_07.model.Author;
import ru.otus.hw_07.model.Book;
import ru.otus.hw_07.model.Genre;

import java.util.List;

public interface LibraryService {

    Long getBooksCount();

    void newBookSave(String title, String comment, String authorName, String genreName);

    Book findBookById(long id);

    List<Book> findAllBooks();

    List<Author> findAllAuthors();

    List<Genre> findAllGenres();

    List<Book> findAllBooksByAuthor(String name);

    void updateBookTitleById(long id, String title);

    void addCommentById(long id, String comment);

    void updateCommentById(long id, String comment);

    void deleteCommentById(long id);

    void bookDeleteById(long id);
}
