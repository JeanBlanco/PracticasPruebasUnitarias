package edu.unac.hotel;

import edu.unac.hotel.exceptions.NoAvailableRoomsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }


    public void addRoom(Room room) {
        rooms.add(room);
    }


    public Reservation bookRoom(RoomType type, Date startDate, Date endDate) throws Exception {

        Optional<Room> availableRoom = rooms.stream()
                .filter(room -> room.getType() == type)
                .filter(room -> isRoomAvailable(room, startDate, endDate))
                .findFirst();


        return availableRoom.map(room -> {
            Reservation newReservation = new Reservation(room, startDate, endDate);
            reservations.add(newReservation);
            return newReservation;
        }).orElseThrow(() -> new NoAvailableRoomsException("No available rooms of requested type"));
    }

    // Método auxiliar para verificar si una habitación está disponible en un rango de fechas
    private boolean isRoomAvailable(Room room, Date startDate, Date endDate) {
        return reservations.stream()
                .filter(reservation -> reservation.getRoom().equals(room))
                .noneMatch(reservation -> reservation.overlaps(startDate, endDate));

    }


    public void cancelReservation(int reservationId) {
        reservations.removeIf(reservation -> reservation.getId() == reservationId);
    }


    public int getTotalReservations() {
        return reservations.size();
    }
}


