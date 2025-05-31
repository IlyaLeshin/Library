package ru.otus.project.dto.user.book.rating;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserBookRatingDto {

    private Long userId;

    private Long bookId;

    private int rating;
}
