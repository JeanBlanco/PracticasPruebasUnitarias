package edu.unac.hotel;

import java.util.Date;

public class Reservation {
    private static int idCounter = 0;
    private int id;
    private Room room;
    private Date startDate;
    private Date endDate;

    public Reservation(Room room, Date startDate, Date endDate) {
        this.id = ++idCounter;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean overlaps(Date start, Date end) {
        return start.before(endDate) && end.after(startDate);
    }
}
