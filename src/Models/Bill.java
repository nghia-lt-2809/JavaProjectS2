package Models;

import java.util.Date;

public class Bill {
    private String id_Bill;
    private String id_Staff;
    private String id_Room;
    private Date days;
    private double totalPrice;

    public Bill() {
    }

    public Bill(String id_Bill, String id_Staff, String id_Room, Date days, double totalPrice) {
        this.id_Bill = id_Bill;
        this.id_Staff = id_Staff;
        this.id_Room = id_Room;
        this.days = days;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id_Bill='" + id_Bill + '\'' +
                ", id_Staff='" + id_Staff + '\'' +
                ", id_Room='" + id_Room + '\'' +
                ", days=" + days +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public String getId_Bill() {
        return id_Bill;
    }

    public void setId_Bill(String id_Bill) {
        this.id_Bill = id_Bill;
    }

    public String getId_Staff() {
        return id_Staff;
    }

    public void setId_Staff(String id_Staff) {
        this.id_Staff = id_Staff;
    }

    public String getId_Room() {
        return id_Room;
    }

    public void setId_Room(String id_Room) {
        this.id_Room = id_Room;
    }

    public Date getDays() {
        return days;
    }

    public void setDays(Date days) {
        this.days = days;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
