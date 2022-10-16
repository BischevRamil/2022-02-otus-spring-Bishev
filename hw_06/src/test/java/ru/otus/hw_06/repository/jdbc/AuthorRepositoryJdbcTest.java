package ru.otus.hw_06.repository.jdbc;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.otus.hw_06.model.Author;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorRepositoryJdbcTest {

    private static final String EXISTING_AUTHOR_NAME = "Zed A. Shaw";
    private static final Long EXISTING_AUTHOR_ID = 2L;

    @Autowired
    private AuthorRepositoryJdbc authorRepositoryJdbc;

    @Test
    @Order(1)
    void shouldNotAddDuplicatedAuthorName() {
        assertThrows(DataIntegrityViolationException.class, () ->
                authorRepositoryJdbc.save(new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME, Collections.emptyList())));
    }

    @Test
    @Order(2)
    void shouldUpdateNameById() {
        final String expectedName = EXISTING_AUTHOR_NAME + " updated";
        authorRepositoryJdbc.updateNameById(EXISTING_AUTHOR_ID, expectedName);
        final Author actualAuthor = authorRepositoryJdbc.findById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor.getName()).isEqualTo(expectedName);
    }

    @Test
    @Order(3)
    void shouldFindById() {
        Author newAuthor = new Author(6, EXISTING_AUTHOR_NAME + " 2", null);
        authorRepositoryJdbc.save(newAuthor);
        assertThat(authorRepositoryJdbc.findById(6)).isEqualTo(newAuthor);
    }

    @Test
    @Order(4)
    void shouldDeleteById() {
        Author newAuthor = new Author(6, EXISTING_AUTHOR_NAME + 2, null);
        authorRepositoryJdbc.save(newAuthor);
        authorRepositoryJdbc.deleteById(6);
        final List<Author> authors = authorRepositoryJdbc.findAll();
        assertThat(authors).doesNotContain(newAuthor);
    }
}
