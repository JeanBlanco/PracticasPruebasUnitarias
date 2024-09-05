package edu.unac.hotel;

import edu.unac.hotel.exceptions.NoAvailableRoomsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
        hotel.addRoom(new Room(1, RoomType.SINGLE));
        hotel.addRoom(new Room(2, RoomType.SINGLE));
        hotel.addRoom(new Room(3, RoomType.DOUBLE));
        hotel.addRoom(new Room(4, RoomType.SUITE));
    }

    @Test
    void ifBookingAvailableRoomOfTypeDoubleThenReservationIsCreated() throws Exception {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24)); // + 1 day

        Reservation reservation = hotel.bookRoom(RoomType.DOUBLE, startDate, endDate);

        assertEquals(1, hotel.getTotalReservations());
        assertEquals(RoomType.DOUBLE, reservation.getRoom().getType());
        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
    }

    @Test
    void ifBookingTwoAvailableRoomsOfTypeDoubleWithDifferentDatesThenBothReservationsAreCreated() throws Exception {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24)); // + 1 day

        Date secondStartDate = new Date(endDate.getTime() + (1000 * 60 * 60 * 25));
        Date secondEndDate = new Date(endDate.getTime() + (1000 * 60 * 60 * 26));

        Reservation reservation = hotel.bookRoom(RoomType.DOUBLE, startDate, endDate);
        Reservation secondReservation = hotel.bookRoom(RoomType.DOUBLE, secondStartDate, secondEndDate);

        assertEquals(2, hotel.getTotalReservations());
        assertEquals(RoomType.DOUBLE, reservation.getRoom().getType());
        assertEquals(RoomType.DOUBLE, secondReservation.getRoom().getType());

        assertEquals(reservation.getRoom().getNumber(), secondReservation.getRoom().getNumber());
    }

    @Test
    void ifBookingTwoAvailableRoomsOfTypeSingleSameDateThenDifferentRoomsAreBooked() throws Exception {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24)); // + 1 day

        Reservation reservation = hotel.bookRoom(RoomType.SINGLE, startDate, endDate);
        Reservation secondReservation = hotel.bookRoom(RoomType.SINGLE, startDate, endDate);

        assertEquals(2, hotel.getTotalReservations());
        assertEquals(RoomType.SINGLE, reservation.getRoom().getType());
        assertEquals(RoomType.SINGLE, secondReservation.getRoom().getType());

        assertNotEquals(reservation.getRoom().getNumber(), secondReservation.getRoom().getNumber());
    }

    @Test
    void ifBookingMoreRoomsThanAvailableSameDateThenThrowsNoAvailableRoomsException() {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24)); // + 1 day

        NoAvailableRoomsException noAvailableRoomsException = assertThrows(NoAvailableRoomsException.class, () -> {
            hotel.bookRoom(RoomType.SINGLE, startDate, endDate);
            hotel.bookRoom(RoomType.SINGLE, startDate, endDate);
            hotel.bookRoom(RoomType.SINGLE, startDate, endDate); // booking should fail
        });

        assertEquals("No available rooms of requested type", noAvailableRoomsException.getMessage());
    }

    @Test
    void ifCancelingAReservationThenReservationCountDecreases() throws Exception {
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24)); // + 1 day

        Reservation reservation = hotel.bookRoom(RoomType.SINGLE, startDate, endDate);
        Reservation secondReservation = hotel.bookRoom(RoomType.SINGLE, startDate, endDate);

        assertEquals(2, hotel.getTotalReservations());
        assertEquals(RoomType.SINGLE, reservation.getRoom().getType());
        assertEquals(RoomType.SINGLE, secondReservation.getRoom().getType());

        hotel.cancelReservation(secondReservation.getId());

        assertEquals(1, hotel.getTotalReservations());
    }
}