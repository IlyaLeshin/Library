package ru.otus.project.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.dto.book.BookDto;
import ru.otus.project.models.Book;

@RequiredArgsConstructor
@Component
public class BookConverter {
    private final AuthorConverter authorConverter;

    private final GenreConverter genreConverter;

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
