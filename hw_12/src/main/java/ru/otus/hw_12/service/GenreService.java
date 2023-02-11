package ru.otus.hw_12.service;


import ru.otus.hw_12.exception.GenreNotFoundException;
import ru.otus.hw_12.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre save(Genre comment);

    Genre findById(String id) throws GenreNotFoundException;

    Genre findByName(String name) throws GenreNotFoundException;

    void deleteById(String id) throws GenreNotFoundException;
}
