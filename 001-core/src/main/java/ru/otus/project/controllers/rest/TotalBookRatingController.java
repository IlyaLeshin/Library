package ru.otus.project.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.dto.book.rating.TotalBookRatingDto;
import ru.otus.project.services.TotalBookRatingService;

@RestController
@AllArgsConstructor
public class TotalBookRatingController {
    private final TotalBookRatingService service;

    @GetMapping("/api/v1/books/{bookId}/rating")
    public TotalBookRatingDto getTotalBookRating(@PathVariable("bookId") long bookId) {
        return service.findByBookId(bookId);
    }
}