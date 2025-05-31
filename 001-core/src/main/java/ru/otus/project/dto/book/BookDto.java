package ru.otus.project.dto.book;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.project.dto.genre.GenreDto;
import ru.otus.project.dto.author.AuthorDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookDto {
    private long id;

    private String title;

    private AuthorDto authorDto;

    private List<GenreDto> genreDtoList;
}
