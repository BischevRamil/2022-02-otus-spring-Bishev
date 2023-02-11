package ru.otus.hw_12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw_12.exception.AuthorNotFoundException;
import ru.otus.hw_12.exception.DuplicateAuthorNameException;
import ru.otus.hw_12.model.Author;
import ru.otus.hw_12.model.Book;
import ru.otus.hw_12.repository.AuthorRepository;
import ru.otus.hw_12.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    final BookRepository bookRepository;
    final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(String id) throws AuthorNotFoundException {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id: %s not found", id)));
    }

    @Override
    public void deleteById(String id) throws AuthorNotFoundException {
        final Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with id: %s not found", id)));
        final List<Book> books = author.getBooks();
        bookRepository.deleteAll(books);
        authorRepository.deleteById(id);
    }

    @Override
    public void delete(Author author) throws AuthorNotFoundException {
        final List<Book> books = author.getBooks();
        bookRepository.deleteAll(books);
        authorRepository.delete(author);
    }

    @Override
    public Author findByName(String name) throws AuthorNotFoundException {
        return authorRepository
                .findByName(name)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with name: %s not found", name)));
    }

    @Override
    public Author save(Author author) throws DuplicateAuthorNameException {
        if (author.getId() == null && (authorRepository.findByName(author.getName()).isPresent())) {
            throw new DuplicateAuthorNameException(
                    "Author with name '" + author.getName() + "' is already define in the scope");
        }
        return authorRepository.save(author);
    }

    @Override
    public Author findAuthorByBook(Book book) throws AuthorNotFoundException {
        return authorRepository
                .findAuthorByBooksContains(book)
                .orElseThrow(() -> new AuthorNotFoundException(String.format("Author with book: %s not found", book.getTitle())));
    }


}
