package ru.otus.hw_06.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw_06.model.Book;
import ru.otus.hw_06.model.Comment;
import ru.otus.hw_06.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class CommentRepositoryJdbc implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public void deleteById(long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }


}
