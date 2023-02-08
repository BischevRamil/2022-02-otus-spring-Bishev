package ru.otus.hw_12.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.hw_12.exception.DuplicateGenreNameException;
import ru.otus.hw_12.model.Genre;
import ru.otus.hw_12.repository.GenreRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenreServiceTest {

    private static final String EXISTING_GENRE_NAME = "Other";

    @Autowired
    private GenreRepository genreRepository;

    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        this.genreService = new GenreServiceImpl(genreRepository);
    }


    @Test
    void shouldfindAll() {
        assertThat(genreRepository.findAll().size()).isEqualTo(6);
    }

    @Test
    void shouldFindById() {
        Genre genre = genreService.save(new Genre("New genre 1"));
        assertThat(genreService.findById(genre.getId())).isEqualTo(genre);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteById() {
        Genre genre = genreService.save(new Genre("New genre 2"));
        genreService.deleteById(genre.getId());
        assertThat(genreService.findAll()).doesNotContain(genre);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldNotAddDuplicatedGenreName() {
        assertThrows(DuplicateGenreNameException.class, () -> genreService.save(new Genre(EXISTING_GENRE_NAME)));
    }
}