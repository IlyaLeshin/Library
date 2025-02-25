package ru.otus.project.security.services;

import ru.otus.project.security.dto.UserDto;

public interface UserService {

    UserDto findByUsername(String username);
}
