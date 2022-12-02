package ru.otus.hw_09.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_09.model.Author;
import ru.otus.hw_09.repository.impl.AuthorCustomizeRepository;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorCustomizeRepository<Author, String> {

}
