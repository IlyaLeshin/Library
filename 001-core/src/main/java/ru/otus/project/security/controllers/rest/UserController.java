package ru.otus.project.security.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.security.converters.UserConverter;
import ru.otus.project.security.dto.ResponseUserDto;
import ru.otus.project.security.services.UserService;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService service;

    private final UserConverter userConverter;

    @GetMapping("/api/v1/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseUserDto getListUserBookRating(@PathVariable("username") String username) {
        return userConverter.dtoToResponseDto(service.findByUsername(username));
    }
}