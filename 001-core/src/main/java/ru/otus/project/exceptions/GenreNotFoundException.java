package ru.otus.project.exceptions;

public class GenreNotFoundException extends EntityNotFoundException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
