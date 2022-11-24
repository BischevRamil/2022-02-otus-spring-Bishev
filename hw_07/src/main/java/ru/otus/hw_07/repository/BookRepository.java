package ru.otus.hw_07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw_07.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
