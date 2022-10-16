package ru.otus.hw_06.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EqualsAndHashCode
@Table(name = "books")
@NamedEntityGraph(name = "book-author-genre-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genre;

}
