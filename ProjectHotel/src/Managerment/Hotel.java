package Managerment;
import  Models.Room;
import  Models.User;
import java.util.ArrayList;

public class Hotel {

    ArrayList<User> userList = new ArrayList<>();
    ArrayList<Room> roomList = new ArrayList<>();

    public  void addNewRoom(Room room){
        roomList.add(room);

    }

    public Hotel(ArrayList<User> userList, ArrayList<Room> roomList) {
        this.userList = userList;
        this.roomList = roomList;
    }
}