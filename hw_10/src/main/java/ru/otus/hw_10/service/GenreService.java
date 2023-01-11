package ru.otus.hw_10.service;


import ru.otus.hw_10.exception.GenreNotFoundException;
import ru.otus.hw_10.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre save(Genre comment);

    Genre findById(String id) throws GenreNotFoundException;

    Genre findByName(String name) throws GenreNotFoundException;

    void deleteById(String id) throws GenreNotFoundException;
}
