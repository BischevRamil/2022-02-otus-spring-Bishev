package ru.otus.hw_08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_08.models.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Optional<Author> findByName(String name);

}
