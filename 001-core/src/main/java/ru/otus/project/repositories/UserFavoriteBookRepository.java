package ru.otus.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.models.UserFavoriteBook;

import java.util.List;
import java.util.Optional;

public interface UserFavoriteBookRepository extends JpaRepository<UserFavoriteBook, Long> {

    Optional<UserFavoriteBook> findByUserIdAndBookId(long userId, long bookId);

    List<UserFavoriteBook> findAllByUserId(long userId);

    void deleteByUserIdAndBookId(long userId, long bookId);

    void deleteAllByUserId(long userId);
}
