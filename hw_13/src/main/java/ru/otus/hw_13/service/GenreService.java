package ru.otus.hw_13.service;


import ru.otus.hw_13.exception.GenreNotFoundException;
import ru.otus.hw_13.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre save(Genre comment);

    Genre findById(String id) throws GenreNotFoundException;

    Genre findByName(String name) throws GenreNotFoundException;

    void deleteById(String id) throws GenreNotFoundException;
}
