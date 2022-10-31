package ru.otus.hw_06.repository;

import ru.otus.hw_06.model.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    void deleteById(long id);
}
