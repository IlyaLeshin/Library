package ru.otus.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.converters.CommentConverter;
import ru.otus.project.dto.CommentCreateDto;
import ru.otus.project.dto.CommentDto;
import ru.otus.project.dto.CommentUpdateDto;
import ru.otus.project.exceptions.BookNotFoundException;
import ru.otus.project.exceptions.CommentNotFoundException;
import ru.otus.project.models.Comment;
import ru.otus.project.repositories.BookRepository;
import ru.otus.project.repositories.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentConverter commentConverter;

    @Override
    public CommentDto findById(long id) {
        return commentRepository.findById(id).map(commentConverter::modelToDto)
                .orElseThrow(() -> new CommentNotFoundException("Comment with id %s not found".formatted(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findAllByBookId(long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id %d not found".formatted(bookId)));
        return book.getComments().stream().map(commentConverter::modelToDto).toList();
    }

    @Override
    public CommentDto create(CommentCreateDto commentCreateDto) {
            String text = commentCreateDto.getText();
            long bookId = commentCreateDto.getBookId();
            return save(0L, text, bookId);

    }

    @Override
    public CommentDto update(CommentUpdateDto commentUpdateDto) {
        long id = commentUpdateDto.getId();
        if (findById(id) != null) {
            String text = commentUpdateDto.getText();
            long bookId = commentUpdateDto.getBookId();
            return save(id, text, bookId);
        }
        throw new CommentNotFoundException("Comment with id %s to the book with id %s not found"
                .formatted(id, commentUpdateDto.getBookId()));
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    private CommentDto save(long id, String text, long bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id %d not found".formatted(bookId)));
        var comment = new Comment(id, text, book);
        return commentConverter.modelToDto(commentRepository.save(comment));
    }
}
