package ru.otus.hw_14.config;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import lombok.RequiredArgsConstructor;
import ru.otus.hw_14.domain.Author;
import ru.otus.hw_14.domain.AuthorItem;
import ru.otus.hw_14.domain.Book;
import ru.otus.hw_14.domain.BookItem;
import ru.otus.hw_14.domain.Genre;
import ru.otus.hw_14.domain.GenreItem;
import ru.otus.hw_14.repository.AuthorRepository;
import ru.otus.hw_14.repository.BookRepository;
import ru.otus.hw_14.repository.GenreRepository;

@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class MigrationBatchConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
    
    private final MongoTemplate mongoTemplate;
    
    private final GenreRepository genreRepository;
    
    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;
    
    private final ModelMapper mapper;

    @Bean
    public ItemReader<Genre> genreReader() {
        return new RepositoryItemReaderBuilder<Genre>()
                .name("genreReader")
                .pageSize(20)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(genreRepository)
                .methodName("findAll")
                .build();
    }
    
    @Bean
    public ItemReader<Author> authorReader() {
        return new RepositoryItemReaderBuilder<Author>()
                .name("authorReader")
                .pageSize(20)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(authorRepository)
                .methodName("findAll")
                .build();
    }
    
    @Bean
    public ItemReader<Book> bookReader() {
        return new RepositoryItemReaderBuilder<Book>()
                .name("bookReader")
                .pageSize(20)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(bookRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemProcessor<? super Object, ? extends Object> genreProcessor() {
        return genre -> mapper.map(genre, GenreItem.class);
    }

    @Bean
    public ItemProcessor<? super Object, ? extends Object> bookProcessor() {
        return book -> mapper.map(book, BookItem.class);
    }

    @Bean
    public ItemProcessor<? super Object, ? extends Object> authorProcessor() {
        return author -> mapper.map(author, AuthorItem.class);
    }

    @Bean
    public ItemWriter<Genre> genreWriter() {
        return new MongoItemWriterBuilder<Genre>()
                .collection("genres")
                .template(mongoTemplate)
                .build();
    }
    
    @Bean
    public ItemWriter<Author> authorWriter() {
        return new MongoItemWriterBuilder<Author>()
                .collection("authors")
                .template(mongoTemplate)
                .build();
    }
    
    @Bean
    public ItemWriter<Book> bookWriter() {
        return new MongoItemWriterBuilder<Book>()
                .collection("books")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step genresImportStep(ItemReader<Genre> reader, ItemProcessor<? super Object, ? extends Object> itemProcessor, ItemWriter writer) {
        return stepBuilderFactory.get("genresImportStep")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }
    
    @Bean
    public Step authorImportStep(ItemReader<Author> reader, ItemProcessor<? super Object, ? extends Object> itemProcessor, ItemWriter writer) {
        return stepBuilderFactory.get("authorsImportStep")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }    

    @Bean
    public Step bookImportStep(ItemReader<Book> reader, ItemProcessor<? super Object, ? extends Object> itemProcessor, ItemWriter writer) {
        return stepBuilderFactory.get("booksImportStep")
                .chunk(5)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }    

    @Bean
    public Job migrateDataJob() {
        return jobBuilderFactory.get("importDataJob")
                .start(genresImportStep(genreReader(), genreProcessor(), genreWriter()))
                .next(authorImportStep(authorReader(), authorProcessor(), authorWriter()))
                .next(bookImportStep(bookReader(), bookProcessor(), bookWriter()))
                .build();
    }
}