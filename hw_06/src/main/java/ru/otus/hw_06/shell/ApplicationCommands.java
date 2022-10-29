package ru.otus.hw_06.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw_06.model.Author;
import ru.otus.hw_06.model.Book;
import ru.otus.hw_06.model.Genre;
import ru.otus.hw_06.service.LibraryService;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class ApplicationCommands {

    private final LibraryService service;

    @ShellMethod(value = "total book count", key = {"count"})
    public String count() {
        return String.format("Books total count is: %s", service.getBooksCount());
    }

    @ShellMethod(value = "add -title 'book title' -comment 'my comment' -author 'Bruce Eckel' -genre 'Other'", key = {"add"})
    public void add(@ShellOption(value = {"-title"}) String title,
                    @ShellOption(value = {"-comment"}, defaultValue = "") String comment,
                    @ShellOption(value = {"-author"}) String authorName,
                    @ShellOption(value = {"-genre"}) String genreName) {
        service.newBookSave(title, comment, authorName, genreName);
    }

    @ShellMethod(value = "find -id 1", key = {"find"})
    public Book findById(@ShellOption(value = {"-id"}) long id) {
        return service.findBookById(id);
    }

    @ShellMethod(value = "find all books", key = {"findb"})
    public List<Book> findAllBooks() {
        return service.findAllBooks();
    }

    @ShellMethod(value = "find all book authors", key = {"finda"})
    public List<Author> findAllAuthors() {
        return service.findAllAuthors();
    }

    @ShellMethod(value = "find all book genres", key = {"findg"})
    public List<Genre> findAllGenres() {
        return service.findAllGenres();
    }

    @ShellMethod(value = "find all author books: findab -name 'Bruce Eckel'", key = {"findab"})
    public List<Book> findAllAuthorBooks(@ShellOption(value = {"-name"}) String name) {
        return service.findAllBooksByAuthor(name);
    }

    @ShellMethod(value = "updatet -id 'id' 'book title'", key = {"updatet"})
    public void updateTitleById(@ShellOption(value = {"-id"}) long id, String title) {
        service.updateBookTitleById(id, title);
    }

    @ShellMethod(value = "updatec -id 'id' 'book comment'", key = {"updatec"})
    public void updateCommentById(@ShellOption(value = {"-id"}) long id, String comment) {
        service.updateCommentById(id, comment);
    }

    @ShellMethod(value = "delete -id", key = {"delete"})
    public void deleteById(@ShellOption(value = {"-id"}) long id) {
        service.bookDeleteById(id);
    }
}
