package ru.otus.project.exceptions;

public class EntityAlreadyExistsException extends LibraryException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
