package ru.otus.hw_12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw_12.exception.GenreNotFoundException;
import ru.otus.hw_12.model.Genre;
import ru.otus.hw_12.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre findById(String id) throws GenreNotFoundException {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(String.format("Genre with id: %s not found", id)));
    }

    @Override
    public Genre findByName(String name) throws GenreNotFoundException {
        return genreRepository.findByName(name)
                .orElseThrow(() -> new GenreNotFoundException(String.format("Genre with name %s not found", name)));
    }

    @Override
    public void deleteById(String id) throws GenreNotFoundException {
        if (genreRepository.findById(id).isEmpty()) {
            throw new GenreNotFoundException(String.format("Genre with id: %s not found", id));
        }
        genreRepository.deleteById(id);
    }
}
