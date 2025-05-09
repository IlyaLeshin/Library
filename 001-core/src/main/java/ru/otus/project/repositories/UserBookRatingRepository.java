package ru.otus.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.models.UserBookRating;

import java.util.List;
import java.util.Optional;

public interface UserBookRatingRepository extends JpaRepository<UserBookRating, Long> {

    Optional<UserBookRating> findByUserIdAndBookId(Long userId, Long bookId);

    List<UserBookRating> findAllByUserId(Long userId);

    List<UserBookRating> findAllByBookId(Long bookId);

    void deleteByUserIdAndBookId(Long userId, Long bookId);
}
