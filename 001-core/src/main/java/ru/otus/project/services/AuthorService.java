package ru.otus.project.services;

import ru.otus.project.dto.author.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();
}
