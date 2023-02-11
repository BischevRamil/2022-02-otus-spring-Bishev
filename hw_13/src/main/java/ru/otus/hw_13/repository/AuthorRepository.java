package ru.otus.hw_13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_13.model.Author;
import ru.otus.hw_13.model.Book;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);

    Optional<Author> findAuthorByBooksContains(Book book);
}
