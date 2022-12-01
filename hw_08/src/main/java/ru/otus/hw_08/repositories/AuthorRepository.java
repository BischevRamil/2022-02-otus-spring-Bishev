package ru.otus.hw_08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_08.models.Author;
import ru.otus.hw_08.repositories.impl.AuthorCustomizeRepository;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorCustomizeRepository<Author, String> {
}
