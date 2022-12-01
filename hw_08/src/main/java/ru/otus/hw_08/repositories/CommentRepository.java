package ru.otus.hw_08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_08.models.Comment;
import ru.otus.hw_08.repositories.impl.CommentCustomizeRepository;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentCustomizeRepository<Comment, String> {
}
