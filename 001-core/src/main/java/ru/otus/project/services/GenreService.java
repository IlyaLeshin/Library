package ru.otus.project.services;

import ru.otus.project.dto.genre.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();
}
