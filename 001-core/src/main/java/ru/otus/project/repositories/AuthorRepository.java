package ru.otus.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
