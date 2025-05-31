package ru.otus.project.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.dto.author.AuthorDto;
import ru.otus.project.services.AuthorService;


import java.util.List;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @GetMapping("/api/v1/authors")
    public List<AuthorDto> getListAuthors() {
        return service.findAll();
    }

}