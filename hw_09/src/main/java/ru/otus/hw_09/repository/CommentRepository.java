package ru.otus.hw_09.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw_09.model.Comment;
import ru.otus.hw_09.repository.impl.CommentCustomizeRepository;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentCustomizeRepository<Comment, String> {

}
