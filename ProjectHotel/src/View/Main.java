package View;


import java.util.ArrayList;

import Managerment.Hotel;
import Models.User;
import Models.Room;
import Models.Order;


public class Main {

    public static void main(String[] args) {
        //fakeData

        // USer:
        User tuyen = new User("Le Tuyen","2000","001202009339","Ha Noi","0378668396");
        System.out.println("\n");
        User Dang = new User("Hai Dang","2002","001348579339","Ha Noi","0911217789");
        ArrayList<User> users = new ArrayList<>();
        users.add(tuyen);
        users.add(Dang);
        System.out.println(users.toString());
        System.out.println("\n");



        //Room:

        Room r1 = new Room("e11","VIP",4000,3);
        Room r2 = new Room("e12","affordable",2000,2);
        Room r3 = new Room("e13","Fake",1000,1);

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);

        System.out.println(rooms.toString());

        //Hotel






    }
}
