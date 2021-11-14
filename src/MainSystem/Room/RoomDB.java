package MainSystem.Room;

import MainSystem.Room.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RoomDB {
    private List<Room> roomList = new ArrayList<>();

    private void getRoomDB(String search){
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        roomList.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement(search);
            rs = stm.executeQuery();
            while (rs.next()){
//                String id = rs.getString("r_id");
//                String type = rs.getString("r_type");
//                double price = rs.getDouble("r_price");
//                String status = rs.getInt("r_status") == 0 ? "Empty" : "Full";
//                Object[] data = {id, type, price, status};
//                model.addRow(data);
//                model.addRow(new Object[]{id, type, price, status});
//                model.addRow(new Object[]{rs.getString("r_id"),
//                        rs.getString("r_type"),
//                        formatter.format(rs.getDouble("r_price")),
//                        rs.getInt("r_status") == 0 ? "Empty" : "Full"});
                Room room = new Room();
                room.setId(rs.getString("r_id"));
                room.setType(rs.getString("r_type"));
                room.setPrice(rs.getDouble("r_price"));
                room.setStatus(rs.getInt("r_status") == 0 ? "Empty" : "Occupied");
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRoomTable(DefaultTableModel model){
        model.setRowCount(0);
        NumberFormat formatter = new DecimalFormat("#,000");
        for (Room r: roomList) {
            model.addRow(new Object[]{r.getId(), r.getType(), formatter.format(r.getPrice()), r.getStatus()});
        }
    }
    
    public void searchRoomByName(DefaultTableModel model, JTextField textField){
        List<Room> searchList = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#,000");
        boolean check = false;
        for (Room r: roomList){
            if (r.getType().toLowerCase().contains(textField.getText().toLowerCase()) && !textField.getText().equals("")){
                searchList.add(r);
                check = true;
            }
            else if (textField.getText().equals("")){
                check = false;
                addRoomTable(model);
            } else if (!check){
                model.setRowCount(0);
            }
        }
        if (check){
            model.setRowCount(0);
            for (Room r: searchList) {
                model.addRow(new Object[]{r.getId(), r.getType(), formatter.format(r.getPrice()), r.getStatus()});            
            }
        }
    }

    public void searchRoomByType(DefaultTableModel model){
        List<Room> searchList = new ArrayList<>();
        NumberFormat formatter = new DecimalFormat("#,000");
        boolean check = false;
        for (Room r: roomList){
            if (r.getStatus().equals("Empty")){
                searchList.add(r);
                check = true;
            } else if (!check){
                model.setRowCount(0);
            }
        }
        if (check){
            model.setRowCount(0);
            for (Room r: searchList) {
                model.addRow(new Object[]{r.getId(), r.getType(), formatter.format(r.getPrice()), r.getStatus()});
            }
        }
    }

    public double getRoomPrice(String id){
        for (Room r: roomList){
            if (r.getId().equals(id)){
                return r.getPrice();
            }
        }
        return 0;
    }

    public void roomAreRenting(){
        getRoomDB("select * from room where r_status = 0");
    }

    public void getAllRoom(){
        getRoomDB("select * from room");
    }
}
