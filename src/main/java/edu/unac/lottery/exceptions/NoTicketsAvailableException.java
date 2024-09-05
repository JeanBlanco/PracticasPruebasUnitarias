package edu.unac.lottery.exceptions;

public class NoTicketsAvailableException extends Exception {
    public NoTicketsAvailableException(String message) {
        super(message);
    }
}
