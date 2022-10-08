package ru.otus.hw_05.repository.jdbc;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import ru.otus.hw_05.model.Genre;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GenreDaoJdbcTest {
    private static final String EXISTING_GENRE_NAME = "Other";

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    void shouldNotAddDuplicatedAuthorName() {
        assertThrows(DuplicateKeyException.class, () -> genreDaoJdbc.add(new Genre(0, EXISTING_GENRE_NAME)));
    }
}
