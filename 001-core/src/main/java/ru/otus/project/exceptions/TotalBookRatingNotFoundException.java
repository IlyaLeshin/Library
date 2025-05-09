package ru.otus.project.exceptions;

public class TotalBookRatingNotFoundException extends EntityNotFoundException {
    public TotalBookRatingNotFoundException(String message) {
        super(message);
    }
}
