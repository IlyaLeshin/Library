package ru.otus.project.dto.user.book.rating;

import jakarta.validation.constraints.NotNull;
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
public class UserBookRatingUpdateDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;

    @NotNull
    private int rating;
}
