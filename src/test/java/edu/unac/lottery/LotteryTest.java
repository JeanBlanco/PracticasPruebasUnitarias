package edu.unac.lottery;

import edu.unac.lottery.exceptions.NoTicketsAvailableException;
import edu.unac.lottery.exceptions.TicketAlreadySoldException;
import edu.unac.lottery.exceptions.TicketNotFoundException;
import edu.unac.lottery.exceptions.TicketNotSoldException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


class LotteryTest {

    private Lottery lottery;
    private RandomNumberGenerator randomNumberGenerator;

    @BeforeEach
    void setUp() {

        randomNumberGenerator = new RandomNumberGenerator() {
            @Override
            public int generate(int upperBound) {
                return 0;
            }
        };

        lottery = new Lottery(randomNumberGenerator);
        lottery.addTicket(new Ticket(1));
        lottery.addTicket(new Ticket(2));
        lottery.addTicket(new Ticket(3));
    }

    @Test
    void buyTicketSuccessfully() throws Exception {
        lottery.buyTicket(1);
        Ticket ticket = lottery.getTickets().get(0);
        assertTrue(ticket.isSold());
    }

    @Test
    void buyTicketAlreadySoldThrowsException() throws Exception {
        lottery.buyTicket(1);
        assertThrows(TicketAlreadySoldException.class, () -> {
            lottery.buyTicket(1);
        });
    }

    @Test
    void buyNonExistingTicketThrowsException() {
        assertThrows(TicketNotFoundException.class, () -> {
            lottery.buyTicket(200000);
        });
    }

    @Test
    void drawWinnerSuccessfully() throws Exception {
        lottery.buyTicket(1);

        Ticket winner = lottery.drawWinner();
        assertEquals(1, winner.getNumber());
        assertTrue(winner.isSold());
    }

    @Test
    void drawWinnerTicketNotSoldThrowsException() {
        assertThrows(TicketNotSoldException.class, () -> {
            lottery.drawWinner();
        });
    }

    @Test
    void drawWinnerNoTicketsAvailableThrowsException() {
        Lottery emptyLottery = new Lottery(randomNumberGenerator);
        assertThrows(NoTicketsAvailableException.class, emptyLottery::drawWinner);
    }

}