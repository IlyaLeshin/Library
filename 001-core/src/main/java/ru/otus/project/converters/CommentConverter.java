package ru.otus.project.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.dto.comment.CommentDto;
import ru.otus.project.dto.comment.CommentUpdateDto;
import ru.otus.project.models.Comment;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    public CommentUpdateDto dtoToUpdateDto(CommentDto commentDto) {
        return new CommentUpdateDto(
                commentDto.getId(),
                commentDto.getText(),
                commentDto.getBookId()
        );
    }

    public CommentDto modelToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setBookId(comment.getBook().getId());
        return commentDto;
    }
}
