package edu.unac.hotel;

public  class Room {
    private int number;
    private RoomType type;

    public Room(int number, RoomType type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }
}