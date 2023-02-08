package ru.otus.hw_13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw_13.exception.AuthorNotFoundException;
import ru.otus.hw_13.exception.BookNotFoundException;
import ru.otus.hw_13.model.Author;
import ru.otus.hw_13.model.Book;
import ru.otus.hw_13.repository.AuthorRepository;
import ru.otus.hw_13.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;
    final AuthorRepository authorRepository;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Book findByTitle(String name) throws BookNotFoundException {
        return bookRepository
                .findBookByTitle(name)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with name: %s not found", name)));
    }

    @Override
    public Book findById(String id) throws BookNotFoundException {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with id: %s not found", id)));
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Book> books) {
        bookRepository.deleteAll(books);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByAuthor(Author author) throws AuthorNotFoundException {
        return authorRepository
                .findByName(author.getName())
                .orElseThrow(() -> new AuthorNotFoundException("Author with name " + author.getName() + " not found"))
                .getBooks();
    }

    @Override
    public Book findBookByAuthorAndTitle(Author author, String title) throws BookNotFoundException {
        final List<Book> books = findAllByAuthor(author);
        return books.stream().filter(b -> b.getTitle().equals(title)).findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with title " + title + " not found for " + author.getName()));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
