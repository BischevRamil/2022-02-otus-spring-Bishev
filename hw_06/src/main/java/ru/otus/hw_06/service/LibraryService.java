package ru.otus.hw_06.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw_06.model.Author;
import ru.otus.hw_06.model.Book;
import ru.otus.hw_06.model.Genre;
import ru.otus.hw_06.repository.AuthorRepository;
import ru.otus.hw_06.repository.BookRepository;
import ru.otus.hw_06.repository.GenreRepository;
import ru.otus.hw_06.repository.jdbc.exception.AuthorNotFoundException;
import ru.otus.hw_06.repository.jdbc.exception.BookNotFoundException;
import ru.otus.hw_06.repository.jdbc.exception.GenreNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public Long getBooksCount() {
        return bookRepository.count();
    }

    @Transactional
    public void newBookSave(String title, String comment, String authorName, String genreName) {
        var author = authorRepository
                .findByName(authorName)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with name '%s' not found", authorName)));
        var genre = genreRepository
                .findByName(genreName)
                .orElseThrow(() -> new GenreNotFoundException(String.format("Author with name '%s' not found", genreName)));
        var book = new Book();
        book.setTitle(title);
        book.setComment(comment);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    public Book findBookById(long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id = %s not found", id)));
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public List<Book> findAllBooksByAuthor(String name) {
        return authorRepository.findByName(name).orElseThrow().getBooks();
    }

    @Transactional
    public void updateBookTitleById(long id, String title) {
        var book = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id = %s not found", id)));
        book.setTitle(title);
        bookRepository.save(book);
    }

    @Transactional
    public void updateCommentById(long id, String comment) {
        var book = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id = %s not found", id)));
        book.setComment(comment);
        bookRepository.save(book);
    }

    public void bookDeleteById(long id) {
        bookRepository.deleteById(id);
    }
}
