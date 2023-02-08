package ru.otus.hw_12.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_12.model.Author;
import ru.otus.hw_12.model.Book;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);

    Optional<Author> findAuthorByBooksContains(Book book);
}
