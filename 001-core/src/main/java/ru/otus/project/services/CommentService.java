package ru.otus.project.services;

import ru.otus.project.dto.CommentCreateDto;
import ru.otus.project.dto.CommentDto;
import ru.otus.project.dto.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    CommentDto findById(long id);

    List<CommentDto> findAllByBookId(long bookId);

    CommentDto create(CommentCreateDto commentCreateDto);

    CommentDto update(CommentUpdateDto commentUpdateDto);

    void deleteById(long id);
}
