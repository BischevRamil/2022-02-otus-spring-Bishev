package ru.otus.hw_12.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_12.model.Author;
import ru.otus.hw_12.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthor(Author author);

    Optional<Book> findBookByAuthorAndTitle(Author author, String title);

    Optional<Book> findBookByTitle(String title);
}
