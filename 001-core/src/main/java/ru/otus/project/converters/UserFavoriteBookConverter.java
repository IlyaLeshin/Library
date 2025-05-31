package ru.otus.project.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.dto.user.favorite.book.UserFavoriteBookCreateDto;
import ru.otus.project.dto.user.favorite.book.UserFavoriteBookDto;
import ru.otus.project.models.UserFavoriteBook;

@RequiredArgsConstructor
@Component
public class UserFavoriteBookConverter {

    public UserFavoriteBookCreateDto dtoToUpdateDto(UserFavoriteBookDto userFavoriteBook) {
        return new UserFavoriteBookCreateDto(
                userFavoriteBook.getUserId(),
                userFavoriteBook.getBookId()
        );
    }

    public UserFavoriteBookDto modelToDto(UserFavoriteBook userFavoriteBook) {
        UserFavoriteBookDto userFavoriteBookDto = new UserFavoriteBookDto();
        userFavoriteBookDto.setUserId(userFavoriteBook.getUserFavoriteBookId().getUserId());
        userFavoriteBookDto.setBookId(userFavoriteBook.getUserFavoriteBookId().getBookId());
        return userFavoriteBookDto;
    }
}
