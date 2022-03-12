package ru.team.scheduler.oapi.exceptions;

public class MatchPasswordException extends RuntimeException {
    public MatchPasswordException(String message) {
        super(message);
    }
}
