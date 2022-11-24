package ru.otus.hw_07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw_07.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
