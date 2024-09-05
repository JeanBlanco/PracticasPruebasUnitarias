package edu.unac.lottery.exceptions;

public class TicketNotSoldException extends Exception {
    public TicketNotSoldException(String message) {
        super(message);
    }
}
