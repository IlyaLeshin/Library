package ru.otus.project.services;

import ru.otus.project.dto.user.favorite.book.UserFavoriteBookCreateDto;
import ru.otus.project.dto.user.favorite.book.UserFavoriteBookDto;

import java.util.List;

public interface UserFavoriteBookService {

    List<UserFavoriteBookDto> findAllByUserId(long id);

    UserFavoriteBookDto findByUserIdAndBookId(long userId, long bookId);

    UserFavoriteBookDto insert(UserFavoriteBookCreateDto userFavoriteBookCreateDto);

    void deleteByUserIdAndBookId(long userId, long bookId);

    void deleteAllByUserId(long userId);
}
