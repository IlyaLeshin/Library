package ru.otus.project.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.dto.genre.GenreDto;
import ru.otus.project.services.GenreService;

import java.util.List;

@RestController
@AllArgsConstructor
public class GenreController {
    private final GenreService service;

    @GetMapping("/api/v1/genres")
    public List<GenreDto> getListAuthors() {
        return service.findAll();
    }

}