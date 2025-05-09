package ru.otus.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.converters.TotalBookRatingConverter;
import ru.otus.project.dto.book.rating.TotalBookRatingDto;
import ru.otus.project.dto.book.rating.TotalBookRatingUpdateDto;
import ru.otus.project.exceptions.BookNotFoundException;
import ru.otus.project.exceptions.TotalBookRatingNotFoundException;
import ru.otus.project.models.TotalBookRating;
import ru.otus.project.models.UserBookRating;
import ru.otus.project.repositories.BookRepository;
import ru.otus.project.repositories.TotalBookRatingRepository;
import ru.otus.project.repositories.UserBookRatingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalBookRatingServiceImpl implements TotalBookRatingService {

    private final TotalBookRatingRepository totalBookRatingRepository;

    private final UserBookRatingRepository userBookRatingRepository;

    private final BookRepository bookRepository;

    private final TotalBookRatingConverter ratingConverter;

    @Override
    @Transactional(readOnly = true)
    public TotalBookRatingDto findByBookId(long bookId) {
        return totalBookRatingRepository.findById(bookId).map(ratingConverter::modelToDto).orElseThrow(() ->
                new TotalBookRatingNotFoundException("TotalBookRating for book with bookId %s not found".formatted(bookId)));
    }

    @Override
    @Transactional
    public TotalBookRatingDto update(TotalBookRatingUpdateDto totalBookRatingUpdateDto) {
        return save(totalBookRatingUpdateDto.getBookId());
    }

    private TotalBookRatingDto save(long bookId) {

        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id %d not found".formatted(bookId)));

        var userBookRatings = userBookRatingRepository.findAllByBookId(bookId);

        double averageBookRating = averageBookRating(userBookRatings);

        var totalBookRating = new TotalBookRating(bookId, averageBookRating, userBookRatings, book);
        return ratingConverter.modelToDto(totalBookRatingRepository.save(totalBookRating));
    }

    private double averageBookRating(List<UserBookRating> userBookRatings) {
        return userBookRatings.stream().map(UserBookRating::getRating).mapToDouble(r -> r).average().orElse(0.0);

    }
}
