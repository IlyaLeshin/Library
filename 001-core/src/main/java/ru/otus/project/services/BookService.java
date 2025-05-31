package ru.otus.project.services;

import ru.otus.project.dto.book.BookCreateDto;
import ru.otus.project.dto.book.BookDto;
import ru.otus.project.dto.book.BookUpdateDto;
import ru.otus.project.dto.book.BookWithCommentsDto;

import java.util.List;

public interface BookService {
    BookDto findById(long id);

    BookWithCommentsDto findWithCommentsById(long id);

    List<BookDto> findAll();

    BookDto insert(BookCreateDto bookDto);

    BookDto update(BookUpdateDto bookDto);

    void deleteById(long id);
}
