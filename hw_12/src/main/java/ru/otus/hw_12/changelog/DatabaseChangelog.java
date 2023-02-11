package ru.otus.hw_12.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw_12.model.Author;
import ru.otus.hw_12.model.Book;
import ru.otus.hw_12.model.Comment;
import ru.otus.hw_12.model.Genre;
import ru.otus.hw_12.repository.AuthorRepository;
import ru.otus.hw_12.repository.BookRepository;
import ru.otus.hw_12.repository.GenreRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "dmitry", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "dmitry")
    public void insertData(AuthorRepository authorRepository, GenreRepository genreRepository,
                           BookRepository bookRepository) {

        Genre genre1 = new Genre("Computer science");
        Genre genre2 = new Genre("Other");
        genreRepository.save(genre1);
        genreRepository.save(genre2);

        Comment comment1 = new Comment("It's an interesting book", LocalDate.now());
        Comment comment2 = new Comment("It's a great book!", LocalDate.now());
        Comment comment3 = new Comment("Boring book...", LocalDate.now());
        Comment comment4 = new Comment("Pretty nice", LocalDate.now());

        final Book book1 = new Book("Thinking in java", genre1, List.of(comment1, comment2));
        final Book book2 = new Book("Learn Python the Hard Way", genre1, List.of(comment1, comment3, comment4));
        final Book book3 = new Book("The Monster", genre2, Collections.emptyList());

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        final Author author1 = new Author("Bruce Eckel", List.of(book1));
        final Author author2 = new Author("Zed A. Shaw", List.of(book2));
        final Author author3 = new Author("Alfred Van Vogt", List.of(book3));
        final Author author4 = new Author("Super Author");

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);

    }
}
