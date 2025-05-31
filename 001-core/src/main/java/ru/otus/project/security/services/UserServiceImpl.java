package ru.otus.project.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.security.converters.UserConverter;
import ru.otus.project.security.dto.UserDto;
import ru.otus.project.security.exceptions.UserNotFoundException;
import ru.otus.project.security.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Override
    @Transactional(readOnly = true)
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username).map(userConverter::modelToDto).orElseThrow(() ->
                new UserNotFoundException("User with username %s not found".formatted(username)));
    }
}
