package ru.otus.project.services;

import ru.otus.project.dto.user.book.rating.UserBookRatingDto;
import ru.otus.project.dto.user.book.rating.UserBookRatingUpdateDto;

import java.util.List;

public interface UserBookRatingService {

    UserBookRatingDto findByUserIdAndBookId(long userId, long bookId);

    List<UserBookRatingDto> findAllByUserId(long userId);

    UserBookRatingDto insertOrUpdate(UserBookRatingUpdateDto userBookRatingUpdateDto);

    void deleteByUserIdAndBookId(long userId, long bookId);
}
