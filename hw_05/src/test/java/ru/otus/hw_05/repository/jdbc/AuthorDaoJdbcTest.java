package ru.otus.hw_05.repository.jdbc;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import ru.otus.hw_05.model.Author;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorDaoJdbcTest {

    private static final String EXISTING_AUTHOR_NAME = "Zed A. Shaw";

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void shouldNotAddDuplicatedAuthorName() {
        assertThrows(DuplicateKeyException.class, () -> authorDaoJdbc.add(new Author(0, EXISTING_AUTHOR_NAME)));
    }
}
