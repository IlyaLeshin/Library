package ru.otus.project.services;

import ru.otus.project.dto.book.rating.TotalBookRatingDto;
import ru.otus.project.dto.book.rating.TotalBookRatingUpdateDto;

public interface TotalBookRatingService {

    TotalBookRatingDto findByBookId(long bookId);

    TotalBookRatingDto update(TotalBookRatingUpdateDto totalBookRatingUpdateDto);

}
