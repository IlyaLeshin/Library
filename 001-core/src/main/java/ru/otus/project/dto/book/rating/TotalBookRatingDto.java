package ru.otus.project.dto.book.rating;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.project.dto.user.book.rating.UserBookRatingDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TotalBookRatingDto {

    private Long bookId;

    private double averageRating;

    private List<UserBookRatingDto> userBookRatings;
}
