package ru.otus.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.converters.UserBookRatingConverter;
import ru.otus.project.dto.user.book.rating.UserBookRatingDto;
import ru.otus.project.dto.user.book.rating.UserBookRatingUpdateDto;
import ru.otus.project.exceptions.BookNotFoundException;
import ru.otus.project.exceptions.UserBookRatingNotFoundException;
import ru.otus.project.models.UserBookRating;
import ru.otus.project.models.UserBookRatingId;
import ru.otus.project.repositories.BookRepository;
import ru.otus.project.repositories.UserBookRatingRepository;
import ru.otus.project.security.exceptions.UserNotFoundException;
import ru.otus.project.security.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBookRatingServiceImpl implements UserBookRatingService {

    private final UserBookRatingRepository ratingRepository;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final UserBookRatingConverter ratingConverter;

    @Override
    @Transactional(readOnly = true)
    public UserBookRatingDto findByUserIdAndBookId(long userId, long bookId) {
        return ratingRepository.findByUserIdAndBookId(bookId, userId).map(ratingConverter::modelToDto).orElseThrow(() ->
                new UserBookRatingNotFoundException("UserBookRating with userId %s and bookId %s not found".formatted(userId, bookId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBookRatingDto> findAllByUserId(long userId) {
        return ratingRepository.findAllByUserId(userId).stream().map(ratingConverter::modelToDto).toList();
    }

    @Override
    @Transactional
    public UserBookRatingDto insertOrUpdate(UserBookRatingUpdateDto userBookRatingUpdateDto) {
        long userId = userBookRatingUpdateDto.getUserId();
        long bookId = userBookRatingUpdateDto.getBookId();
        int rating = userBookRatingUpdateDto.getRating();

        return save(userId, bookId, rating);
    }

    @Override
    public void deleteByUserIdAndBookId(long userId, long bookId) {
        ratingRepository.deleteByUserIdAndBookId(userId, bookId);
    }

    private UserBookRatingDto save(long userId, long bookId, int rating) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id %d not found".formatted(bookId)));

        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id %d not found".formatted(bookId)));

        var userBookRating = ratingRepository.save(new UserBookRating(new UserBookRatingId(userId, bookId), rating, user, book));

        return ratingConverter.modelToDto(userBookRating);
    }
}
