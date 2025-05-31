package ru.otus.project.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.dto.user.book.rating.UserBookRatingDto;
import ru.otus.project.dto.user.book.rating.UserBookRatingUpdateDto;
import ru.otus.project.services.UserBookRatingService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserBookRatingController {
    private final UserBookRatingService service;

    @GetMapping("/api/v1/users/{userId}/ratings/books")
    @ResponseStatus(HttpStatus.OK)
    public List<UserBookRatingDto> getListUserBookRating(@PathVariable("userId") long userId) {
        return service.findAllByUserId(userId);
    }

    @GetMapping("/api/v1/users/{userId}/ratings/books/{bookId}")
    public UserBookRatingDto getUserBookRating(@PathVariable("userId") long userId,
                                               @PathVariable("bookId") long bookId) {
        return service.findByUserIdAndBookId(userId, bookId);
    }

    @PostMapping("/api/v1/users/{userId}/ratings/books/{bookId}")
    public UserBookRatingDto createUserBookRating(@Valid @RequestBody UserBookRatingUpdateDto userBookRatingUpdateDto) {
        return service.insertOrUpdate(userBookRatingUpdateDto);
    }

    @PutMapping("/api/v1/users/{userId}/ratings/books/{bookId}")
    public UserBookRatingDto updateUserBookRating(@Valid @RequestBody UserBookRatingUpdateDto userBookRatingUpdateDto) {
        return service.insertOrUpdate(userBookRatingUpdateDto);
    }

    @DeleteMapping("/api/v1/users/{userId}/ratings/books/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserBookRating(@PathVariable("userId") long userId, @PathVariable("bookId") long bookId) {
        service.deleteByUserIdAndBookId(userId, bookId);
    }
}