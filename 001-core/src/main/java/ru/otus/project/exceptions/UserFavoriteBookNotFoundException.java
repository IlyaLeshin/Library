package ru.otus.project.exceptions;

public class UserFavoriteBookNotFoundException extends EntityNotFoundException {
    public UserFavoriteBookNotFoundException(String message) {
        super(message);
    }
}
