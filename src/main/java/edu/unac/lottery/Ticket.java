package edu.unac.lottery;

public class Ticket {
    private int number;
    private boolean isSold;

    public Ticket(int number) {
        this.number = number;
        this.isSold = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }
}
