package ru.otus.hw_08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_08.models.Book;
import ru.otus.hw_08.repositories.impl.BookCustomizeRepository;

public interface BookRepository extends MongoRepository<Book, String>, BookCustomizeRepository<Book, String> {
}
