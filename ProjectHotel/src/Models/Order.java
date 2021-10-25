package Models;

import java.time.LocalDate;

public class Order {
    private LocalDate checkIn;
    private LocalDate checkOut;
    private User user;
    private Room room;

    public Order() {
    }

    public Order(LocalDate checkIn, LocalDate checkOut, User user, Room room) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.user = user;
        this.room = room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        this.room.setStatus(false);
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
