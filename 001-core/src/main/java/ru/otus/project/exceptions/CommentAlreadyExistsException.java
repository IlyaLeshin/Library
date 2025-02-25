package ru.otus.project.exceptions;

public class CommentAlreadyExistsException extends EntityAlreadyExistsException {
    public CommentAlreadyExistsException(String message) {
        super(message);
    }
}
