package ru.otus.hw_14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.otus.hw_14.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
}
