package ru.otus.project.exceptions;

public class BookAlreadyExistsException extends EntityAlreadyExistsException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
