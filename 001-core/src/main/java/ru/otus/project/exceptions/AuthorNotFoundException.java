package ru.otus.project.exceptions;

public class AuthorNotFoundException extends EntityNotFoundException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
