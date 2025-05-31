package ru.otus.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.models.TotalBookRating;

public interface TotalBookRatingRepository extends JpaRepository<TotalBookRating, Long> {
}
