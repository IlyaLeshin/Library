package ru.otus.project.security.exceptions;

public class EntityNotFoundException extends LibrarySecurityException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
