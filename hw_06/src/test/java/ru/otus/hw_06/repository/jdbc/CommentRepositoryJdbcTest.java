package ru.otus.hw_06.repository.jdbc;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw_06.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentRepositoryJdbcTest {

    @Autowired
    private CommentRepositoryJdbc commentRepositoryJdbc;

    @Test
    @Order(1)
    void shouldAddNewCommentAndFindById() {
        var comment = new Comment(12L, "new Comment");
        commentRepositoryJdbc.save(comment);
        assertEquals(commentRepositoryJdbc.findById(12).get().getComment(), "new Comment");
    }



    @Test
    @Order(2)
    void shouldDeleteById() {
        var comment = new Comment(12L, "new Comment");
        commentRepositoryJdbc.save(comment);
        commentRepositoryJdbc.deleteById(12);
        assertThat(commentRepositoryJdbc.findById(12)).isEmpty();
    }
}
