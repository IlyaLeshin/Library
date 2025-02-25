package ru.otus.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.converters.UserConverter;
import ru.otus.project.dto.UserDto;
import ru.otus.project.exceptions.UserNotFoundException;
import ru.otus.project.repositories.UserRepository;

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
