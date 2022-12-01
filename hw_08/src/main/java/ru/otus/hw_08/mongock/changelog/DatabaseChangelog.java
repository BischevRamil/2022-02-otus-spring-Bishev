package ru.otus.hw_08.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw_08.models.Author;
import ru.otus.hw_08.models.Book;
import ru.otus.hw_08.models.Comment;
import ru.otus.hw_08.models.Genre;
import ru.otus.hw_08.repositories.AuthorRepository;
import ru.otus.hw_08.repositories.BookRepository;
import ru.otus.hw_08.repositories.CommentRepository;
import ru.otus.hw_08.repositories.GenreRepository;

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
                           BookRepository bookRepository, CommentRepository commentRepository) {

        Genre genre1 = new Genre("Сomputer science");
        Genre genre2 = new Genre("Other");
        genreRepository.save(genre1);
        genreRepository.save(genre2);

        Comment comment1 = new Comment("It's an interesting book", LocalDate.now());
        Comment comment2 = new Comment("It's a great book!", LocalDate.now());
        Comment comment3 = new Comment("Boring book...", LocalDate.now());
        Comment comment4 = new Comment("Pretty nice", LocalDate.now());
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
        commentRepository.save(comment4);

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
