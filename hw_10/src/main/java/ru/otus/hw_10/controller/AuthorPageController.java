package ru.otus.hw_10.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.hw_10.exception.AuthorNotFoundException;
import ru.otus.hw_10.exception.DuplicateAuthorBookException;
import ru.otus.hw_10.exception.DuplicateAuthorNameException;
import ru.otus.hw_10.exception.DuplicateGenreNameException;
import ru.otus.hw_10.model.Author;
import ru.otus.hw_10.service.AuthorService;
import ru.otus.hw_10.service.BookService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthorPageController {
    private final BookService bookService;
    private final AuthorService authorService;


    @GetMapping("/author")
    public String newAuthorPage(Model model) {
        model.addAttribute("author", new Author());
        return "newauthor";
    }

    @PostMapping("/author")
    public String addBook(@ModelAttribute(value = "author") Author author) {
        author.setName(author.getName());
        authorService.save(author);
        return "redirect:/";
    }

    @PostMapping("/author/{id}/update")
    public String updateAuthor(@PathVariable("id") String id, @ModelAttribute(value = "author") Author author) {
        final Author authorFromRepo = authorService.findById(id);
        authorFromRepo.setName(author.getName());
        authorService.save(authorFromRepo);
        return "redirect:/author/{id}";
    }

    @GetMapping("/author/{id}/delete")
    public String deleteAuthor(@PathVariable("id") String id) {
        bookService.deleteAll(bookService.findAllByAuthor(authorService.findById(id)));
        authorService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/author/{id}")
    public String authorPage(@PathVariable("id") String id, Model model) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "author";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AuthorNotFoundException.class)
    public ModelAndView handleNotFound(Exception exception) {

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            DuplicateAuthorBookException.class,
            DuplicateGenreNameException.class,
            DuplicateAuthorNameException.class
    })
    public ModelAndView handleDuplicated(Exception exception) {

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("409");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}
