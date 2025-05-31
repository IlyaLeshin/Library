package ru.otus.project.services;

import ru.otus.project.dto.comment.CommentCreateDto;
import ru.otus.project.dto.comment.CommentDto;
import ru.otus.project.dto.comment.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    CommentDto findById(long id);

    List<CommentDto> findAllByBookId(long bookId);

    CommentDto create(CommentCreateDto commentCreateDto);

    CommentDto update(CommentUpdateDto commentUpdateDto);

    void deleteById(long id);
}
