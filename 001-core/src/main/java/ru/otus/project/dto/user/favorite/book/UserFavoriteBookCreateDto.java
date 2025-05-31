package ru.otus.project.dto.user.favorite.book;

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
public class UserFavoriteBookCreateDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;
}
