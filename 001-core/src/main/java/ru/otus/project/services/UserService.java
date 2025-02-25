package ru.otus.project.services;

import ru.otus.project.dto.UserDto;

public interface UserService {

    UserDto findByUsername(String username);
}
