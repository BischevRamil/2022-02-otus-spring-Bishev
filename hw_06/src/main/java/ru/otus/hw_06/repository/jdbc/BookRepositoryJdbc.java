package ru.otus.hw_06.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw_06.model.Book;
import ru.otus.hw_06.model.Genre;
import ru.otus.hw_06.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class BookRepositoryJdbc implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return em.createQuery("select count(e) from Book e", Long.class).getSingleResult();
    }

    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }


    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }
}
