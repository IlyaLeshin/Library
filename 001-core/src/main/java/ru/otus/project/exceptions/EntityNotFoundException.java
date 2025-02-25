package ru.otus.project.exceptions;

public class EntityNotFoundException extends LibraryException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
