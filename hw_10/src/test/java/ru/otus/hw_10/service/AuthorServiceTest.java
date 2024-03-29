package ru.otus.hw_10.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.hw_10.exception.BookNotFoundException;
import ru.otus.hw_10.exception.DuplicateAuthorNameException;
import ru.otus.hw_10.model.Author;
import ru.otus.hw_10.model.Book;
import ru.otus.hw_10.repository.AuthorRepository;
import ru.otus.hw_10.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorServiceTest {

    private static final String EXISTING_AUTHOR_NAME = "Zed A. Shaw";
    private static final int STARTED_AUTHOR_COUNT = 4;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private AuthorServiceImpl authorService;

    private BookService bookService;

    @BeforeEach
    public void setUp() {
        this.bookService = new BookServiceImpl(bookRepository, authorRepository);
        this.authorService = new AuthorServiceImpl(bookRepository, authorRepository);
    }


    @Test
    void shouldfindAll() {
        assertThat(authorService.findAll().size()).isEqualTo(STARTED_AUTHOR_COUNT);
    }

    @Test
    void shouldFindAuthorBooks() {
        Author author = authorService.findByName(EXISTING_AUTHOR_NAME);
        List<Book> books = author.getBooks();
        assertThat(books.get(0))
                .isEqualTo(bookService.findBookByAuthorAndTitle(author, "Learn Python the Hard Way"));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldUpdateNameById() {
        var existingAuthorId = authorService.findByName(EXISTING_AUTHOR_NAME).getId();
        final String expectedName = EXISTING_AUTHOR_NAME + " updated";
        final Author author = authorService.findByName(EXISTING_AUTHOR_NAME);
        author.setName(expectedName);
        authorService.save(author);
        final Author actualAuthor = authorService.findById(existingAuthorId);
        assertThat(actualAuthor.getName()).isEqualTo(expectedName);
    }

    @Test
    void shouldFindById() {
        Author authorFromRepo = authorService.save(new Author(EXISTING_AUTHOR_NAME + 2));
        final String authorId = authorFromRepo.getId();
        assertThat(authorService.findById(authorId)).isEqualTo(authorFromRepo);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteById() {
        final String authorName = EXISTING_AUTHOR_NAME + 3;
        final Author authorFromRepo = authorService.save(new Author(authorName));
        authorService.deleteById(authorFromRepo.getId());
        assertThat(authorService.findAll()).doesNotContain(authorFromRepo);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldNotAddDuplicatedAuthorName() {
        assertThrows(DuplicateAuthorNameException.class, () ->
                authorService.save(new Author(EXISTING_AUTHOR_NAME)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldDeleteAllBooksIfAuthorDeleted() {
        assertThat(bookService.findByTitle("Learn Python the Hard Way")).isInstanceOf(Book.class);
        authorService.delete(authorService.findByName(EXISTING_AUTHOR_NAME));
        assertThrows(BookNotFoundException.class, () -> bookService.findByTitle("Learn Python the Hard Way"));
    }


}