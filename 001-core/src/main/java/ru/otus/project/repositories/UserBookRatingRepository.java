package ru.otus.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.models.UserBookRating;

import java.util.Optional;
import java.util.Set;

public interface UserBookRatingRepository extends JpaRepository<UserBookRating, Long> {

    Optional<UserBookRating> findByUserIdAndBookId(Long userId, Long bookId);

    Set<UserBookRating> findAllByUserId(Long userId);

    Set<UserBookRating> findAllByBookId(Long bookId);

    void deleteByUserIdAndBookId(Long userId, Long bookId);
}
