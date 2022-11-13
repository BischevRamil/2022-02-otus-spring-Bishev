-- DROP TABLE IF EXISTS GENRES CASCADE ;
-- DROP TABLE IF EXISTS BOOKS CASCADE ;
-- DROP TABLE IF EXISTS AUTHORS CASCADE ;
--
-- CREATE TABLE AUTHORS
-- (
--     ID   BIGINT PRIMARY KEY,
--     NAME VARCHAR(255) UNIQUE
-- );
--
-- CREATE TABLE GENRES
-- (
--     ID   BIGINT PRIMARY KEY,
--     NAME VARCHAR(255) UNIQUE
-- );
--
-- CREATE TABLE BOOKS
-- (
--     ID        BIGINT PRIMARY KEY,
--     TITLE     VARCHAR(255),
--     COMMENT   VARCHAR(255),
--     AUTHOR_ID BIGINT REFERENCES AUTHORS (ID),
--     GENRE_ID  BIGINT REFERENCES GENRES (ID)
-- );

insert into authors (id, `name`)
values (1, 'Bruce Eckel');
insert into authors (id, `name`)
values (2, 'Zed A. Shaw');
insert into authors (id, `name`)
values (3, 'Alfred Van Vogt');
insert into authors (id, `name`)
values (4, 'Super Author');

insert into genres (id, `name`)
values (1, 'Computer science');
insert into genres (id, `name`)
values (2, 'Other');

insert into books (id, `title`, author_id, genre_id)
values (1, 'Thinking in java', 1, 1);
insert into books (id, `title`, author_id, genre_id)
values (2, 'Learn Python the Hard Way', 2, 1);
insert into books (id, `title`, author_id, genre_id)
values (3, 'The Monster', 3, 2);