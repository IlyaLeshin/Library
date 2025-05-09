package ru.otus.project.exceptions;

public class UserBookRatingNotFoundException extends EntityNotFoundException {
    public UserBookRatingNotFoundException(String message) {
        super(message);
    }
}
