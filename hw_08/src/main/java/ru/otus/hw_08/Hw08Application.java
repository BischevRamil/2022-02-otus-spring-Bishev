package ru.otus.hw_08;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.hw_08.repositories.AuthorRepository;
import ru.otus.hw_08.repositories.BookRepository;
import ru.otus.hw_08.repositories.GenreRepository;

@EnableMongoRepositories(basePackages = "ru.otus.hw_08.repositories")
@EnableMongock
@SpringBootApplication
public class Hw08Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Hw08Application.class);

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
