package ru.otus.hw_10;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.hw_10.repository.AuthorRepository;
import ru.otus.hw_10.repository.BookRepository;
import ru.otus.hw_10.repository.GenreRepository;

@EnableMongoRepositories(basePackages = "ru.otus.hw_10.repository")
@EnableMongock
@SpringBootApplication
public class Hw10Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Hw10Application.class, args);

        System.out.println("\n\n\n----------------------------------------------\n\n");
        System.out.println("Книги в БД:");
        BookRepository bookRepository = context.getBean(BookRepository.class);
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("\n\n----------------------------------------------");

        System.out.println("Авторы в БД:");
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        authorRepository.findAll().forEach(System.out::println);
        System.out.println("\n\n----------------------------------------------");

        System.out.println("Жанры в БД:");
        GenreRepository genreRepository = context.getBean(GenreRepository.class);
        genreRepository.findAll().forEach(System.out::println);
        System.out.println("\n\n----------------------------------------------");
    }

}
