package ru.otus.project.converters;

import org.springframework.stereotype.Component;
import ru.otus.project.dto.GenreDto;
import ru.otus.project.models.Genre;

@Component
public class GenreConverter {

    public GenreDto modelToDto(Genre genre) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(genre.getId());
        genreDto.setName(genre.getName());
        return genreDto;
    }
}
