package ru.otus.hw_09.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_09.model.Book;
import ru.otus.hw_09.repository.impl.BookCustomizeRepository;

public interface BookRepository extends MongoRepository<Book, String>, BookCustomizeRepository<Book, String> {

}
