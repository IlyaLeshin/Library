package ru.otus.project.security.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.project.security.dto.UserDto;
import ru.otus.project.security.models.Role;
import ru.otus.project.security.models.User;

@RequiredArgsConstructor
@Component
public class UserConverter {

    public UserDto modelToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles().stream()
                .map(Role::getName).toList());
        return userDto;
    }
}
