package ru.otus.project.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.security.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
