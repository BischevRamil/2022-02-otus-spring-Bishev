package ru.otus.hw_13.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.hw_13.exception.DuplicateAuthorNameException;
import ru.otus.hw_13.model.Author;
import ru.otus.hw_13.model.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorRepositoryTest {
    private static final String EXISTING_AUTHOR_NAME = "Zed A. Shaw";
    private static final int STARTED_AUTHOR_COUNT = 4;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldfindAll() {
        assertThat(authorRepository.findAll().size()).isEqualTo(STARTED_AUTHOR_COUNT);
    }

    @Test
    void shouldFindAuthorBooks() {
        Author author = authorRepository.findByName(EXISTING_AUTHOR_NAME).get();
        List<Book> books = author.getBooks();
        assertThat(books.get(0))
                .isEqualTo(bookRepository.findBookByAuthorAndTitle(author, "Learn Python the Hard Way").get());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldUpdateNameById() {
        var existingAuthorId = authorRepository.findByName(EXISTING_AUTHOR_NAME).get().getId();
        final String expectedName = EXISTING_AUTHOR_NAME + " updated";
        final Author author = authorRepository.findByName(EXISTING_AUTHOR_NAME).get();
        author.setName(expectedName);
        authorRepository.save(author);
        final Author actualAuthor = authorRepository.findById(existingAuthorId).get();
        assertThat(actualAuthor.getName()).isEqualTo(expectedName);
    }

    @Test
    void shouldFindById() {
        Author authorFromRepo = authorRepository.save(new Author(EXISTING_AUTHOR_NAME + 2));
        final String authorId = authorFromRepo.getId();
        assertThat(authorRepository.findById(authorId).get()).isEqualTo(authorFromRepo);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteById() {
        final String authorName = EXISTING_AUTHOR_NAME + 3;
        final Author authorFromRepo = authorRepository.save(new Author(authorName));
        authorRepository.deleteById(authorFromRepo.getId());
        assertThat(authorRepository.findAll()).doesNotContain(authorFromRepo);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldNotAddDuplicatedAuthorName() {
        assertThrows(DuplicateAuthorNameException.class, () ->
                authorRepository.save(new Author(EXISTING_AUTHOR_NAME)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteAllBooksIfAuthorDeleted() {
        authorRepository.delete(authorRepository.findByName(EXISTING_AUTHOR_NAME).orElseThrow(AssertionError::new));

    }
}
