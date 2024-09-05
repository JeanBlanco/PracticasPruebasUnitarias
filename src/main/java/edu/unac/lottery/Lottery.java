package edu.unac.lottery;

import edu.unac.lottery.exceptions.NoTicketsAvailableException;
import edu.unac.lottery.exceptions.TicketAlreadySoldException;
import edu.unac.lottery.exceptions.TicketNotFoundException;
import edu.unac.lottery.exceptions.TicketNotSoldException;

import java.util.ArrayList;
import java.util.List;

public class Lottery {
    private List<Ticket> tickets;
    private RandomNumberGenerator randomNumberGenerator;

    public Lottery(RandomNumberGenerator randomNumberGenerator) {
        this.tickets = new ArrayList<>();
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void buyTicket(int ticketNumber) throws Exception {
        boolean ticketFound = false;
        for (Ticket ticket : tickets) {
            if (ticket.getNumber() == ticketNumber) {
                if (ticket.isSold()) {
                    throw new TicketAlreadySoldException("Ticket has already been sold");
                }
                ticket.setSold(true);
                ticketFound = true;
                break;
            }
        }
        if (!ticketFound) {
            throw new TicketNotFoundException("Ticket not found");
        }
    }

    public Ticket drawWinner() throws TicketNotSoldException, NoTicketsAvailableException {
        if (tickets.isEmpty()) {
            throw new NoTicketsAvailableException("No tickets available");
        }

        int winnerIndex = randomNumberGenerator.generate(tickets.size());
        Ticket winningTicket = tickets.get(winnerIndex);

        if (winningTicket.isSold()) {
            return winningTicket;
        } else {
            throw new TicketNotSoldException("Winning ticket number " + winningTicket.getNumber() + " has not been sold");
        }
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
