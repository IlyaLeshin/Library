package ru.otus.project.services;

import ru.otus.project.dto.BookCreateDto;
import ru.otus.project.dto.BookDto;
import ru.otus.project.dto.BookUpdateDto;
import ru.otus.project.dto.BookWithCommentsDto;

import java.util.List;

public interface BookService {
    BookDto findById(long id);

    BookWithCommentsDto findWithCommentsById(long id);

    List<BookDto> findAll();

    BookDto insert(BookCreateDto bookDto);

    BookDto update(BookUpdateDto bookDto);

    void deleteById(long id);
}
