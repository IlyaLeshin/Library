package ru.otus.project.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.dto.book.rating.TotalBookRatingDto;
import ru.otus.project.dto.book.rating.TotalBookRatingUpdateDto;
import ru.otus.project.services.TotalBookRatingService;

@RestController
@AllArgsConstructor
public class TotalBookRatingController {
    private final TotalBookRatingService service;

    @GetMapping("/api/v1/books/{bookId}/rating")
    public TotalBookRatingDto getTotalBookRating(@PathVariable("bookId") long bookId) {
        return service.findByBookId(bookId);
    }

    @PutMapping("/api/v1/books/{bookId}/rating")
    public TotalBookRatingDto updateUserBookRating(@Valid @RequestBody TotalBookRatingUpdateDto totalBookRatingUpdateDto) {
        return service.update(totalBookRatingUpdateDto);
    }
}