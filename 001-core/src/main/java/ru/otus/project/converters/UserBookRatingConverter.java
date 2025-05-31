package ru.otus.project.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.dto.user.book.rating.UserBookRatingDto;
import ru.otus.project.models.UserBookRating;

@RequiredArgsConstructor
@Component
public class UserBookRatingConverter {

    public UserBookRatingDto modelToDto(UserBookRating userBookRating) {
        UserBookRatingDto userBookRatingDto = new UserBookRatingDto();
        userBookRatingDto.setUserId(userBookRating.getUser().getId());
        userBookRatingDto.setBookId(userBookRating.getBook().getId());
        userBookRatingDto.setRating(userBookRating.getRating());
        return userBookRatingDto;
    }
}
