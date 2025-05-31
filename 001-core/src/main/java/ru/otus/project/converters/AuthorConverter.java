package ru.otus.project.converters;

import org.springframework.stereotype.Component;
import ru.otus.project.dto.author.AuthorDto;
import ru.otus.project.models.Author;

@Component
public class AuthorConverter {

    public AuthorDto modelToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFullName(author.getFullName());
        return authorDto;
    }
}
