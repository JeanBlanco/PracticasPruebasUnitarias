package edu.unac.lottery.exceptions;

public class TicketAlreadySoldException extends Exception {
    public TicketAlreadySoldException(String message) {
        super(message);
    }
}
