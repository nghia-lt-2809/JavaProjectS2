package Models;

public class Room {
    private  String name;
    private String type;
    private double price;
    private  boolean status;
    private  int numberOfBeds; // số giường ngủ


    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }




    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

    public Room(String name, String type, double price, int numberOfBeds) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.status = status;
        this.numberOfBeds = numberOfBeds;
        this.status = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
