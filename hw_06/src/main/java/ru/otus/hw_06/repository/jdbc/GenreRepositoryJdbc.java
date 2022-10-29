package ru.otus.hw_06.repository.jdbc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw_06.model.Genre;
import ru.otus.hw_06.repository.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJdbc implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :name", Genre.class);
        return Optional.ofNullable(query.getSingleResult());

//        query.setParameter("name", name);
//        try {
//            return query.getSingleResult();
//        } catch (NoResultException e) {
//            throw new GenreNotFoundException("Author with name '" + name + "' not found");
//        }
    }

//    @Override
//    @Transactional
//    public void updateNameById(long id, String name) {
//        findById(id).setName(name);
//    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Genre genre = em.find(Genre.class, id);
        em.remove(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
//        final Optional<Genre> optionalGenre = Optional.ofNullable(em.find(Genre.class, id));
//        if (optionalGenre.isPresent()) {
//            return optionalGenre.get();
//        } else {
//            throw new GenreNotFoundException("Genre with id=" + "'" + id + " not found");
//        }
    }

}
