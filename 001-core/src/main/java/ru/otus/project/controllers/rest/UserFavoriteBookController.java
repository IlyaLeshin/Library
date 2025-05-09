package ru.otus.project.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.dto.user.favorite.book.UserFavoriteBookCreateDto;
import ru.otus.project.dto.user.favorite.book.UserFavoriteBookDto;
import ru.otus.project.services.UserFavoriteBookService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserFavoriteBookController {
    private final UserFavoriteBookService service;

    @GetMapping("/api/v1/users/{userId}/favorites/books")
    @ResponseStatus(HttpStatus.OK)
    public List<UserFavoriteBookDto> getUserFavoriteBookList(@PathVariable("userId") long userId) {
        return service.findAllByUserId(userId);
    }

    @GetMapping("/api/v1/users/{userId}/favorites/books/{bookId}")
    public UserFavoriteBookDto getUserFavoriteBook(@PathVariable("userId") long userId, @PathVariable long bookId) {
        return service.findByUserIdAndBookId(userId, bookId);
    }

    @PostMapping("/api/v1/users/{userId}/favorites/books/{bookId}")
    public UserFavoriteBookDto addBookToUserFavorites(@Valid @RequestBody UserFavoriteBookCreateDto userFavoriteBookCreateDto) {
        return service.insert(userFavoriteBookCreateDto);
    }

    @DeleteMapping("/api/v1/users/{userId}/favorites/books/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookFromUserFavorites(@PathVariable("userId") long userId, @PathVariable long bookId) {
        service.deleteByUserIdAndBookId(userId, bookId);
    }

    @DeleteMapping("/api/v1/users/{userId}/favorites/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllBooksFromUserFavorites(@PathVariable("userId") long userId) {
        service.deleteAllByUserId(userId);
    }
}