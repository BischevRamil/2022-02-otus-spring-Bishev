package ru.otus.hw_13.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw_13.model.Book;
import ru.otus.hw_13.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexPageController {
    private final BookService bookService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

}
