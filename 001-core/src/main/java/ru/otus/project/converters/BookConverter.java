package ru.otus.project.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.dto.BookDto;
import ru.otus.project.dto.BookUpdateDto;
import ru.otus.project.dto.GenreDto;
import ru.otus.project.models.Book;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

    public BookUpdateDto dtoToUpdateDto(BookDto bookDto) {
        return new BookUpdateDto(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthorDto().getId(),
                bookDto.getGenreDtoList().stream().map(GenreDto::getId).collect(Collectors.toSet())
        );
    }

    public BookDto modelToDto(Book book) {

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthorDto(authorConverter.modelToDto(book.getAuthor()));
        bookDto.setGenreDtoList(book.getGenres().stream()
                .map(genreConverter::modelToDto).toList());
        return bookDto;
    }
}
