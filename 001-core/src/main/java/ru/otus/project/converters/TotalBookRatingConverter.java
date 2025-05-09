package ru.otus.project.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.dto.book.rating.TotalBookRatingDto;
import ru.otus.project.models.TotalBookRating;

@RequiredArgsConstructor
@Component
public class TotalBookRatingConverter {

    private final UserBookRatingConverter userBookRatingConverter;

    public TotalBookRatingDto modelToDto(TotalBookRating totalBookRating) {
        TotalBookRatingDto totalBookRatingDto = new TotalBookRatingDto();
        totalBookRatingDto.setBookId(totalBookRating.getBookId());
        totalBookRatingDto.setUserBookRatings(totalBookRating.getUserBookRatings().stream()
                .map(userBookRatingConverter::modelToDto).toList());
        totalBookRatingDto.setAverageRating(totalBookRating.getAverageRating());
        return totalBookRatingDto;
    }
}
