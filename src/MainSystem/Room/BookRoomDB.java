package MainSystem.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BookRoomDB {
    private List<BookRoom> bookRoomList = new ArrayList<>();

    private void getBookRoomDB(String search){
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        bookRoomList.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement(search);
            rs = stm.executeQuery();
            while (rs.next()){
                BookRoom bookRoom = new BookRoom();
                bookRoom.setBookingCode(rs.getString("booking_code"));
                bookRoom.setEmployeeId(rs.getString("employee_id"));
                bookRoom.setCustomerId(rs.getString("customer_id"));
                bookRoom.setRoomId(rs.getString("room_id"));
                bookRoom.setCheckIn(rs.getString("check_in"));
                bookRoom.setCheckOut(rs.getString("check_out"));
                bookRoomList.add(bookRoom);
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

    public void setBookRoomDB(String bookCode, String customerId, String checkIn, String roomId, String employeeId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("insert into bookroom(booking_code, employee_id, customer_id, room_id, check_in) " +
                    "values " +
                    "(?,?,?,?,?)");
            stm.setString(1, bookCode);
            stm.setString(2, employeeId);
            stm.setString(3, customerId);
            stm.setString(4, roomId);
            stm.setString(5, checkIn);
            if (stm.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Successful room rental");
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062){
                JOptionPane.showMessageDialog(null, "Can not book room because customer has book another room", "Error",JOptionPane.ERROR_MESSAGE);
            } else if (e.getErrorCode() == 1452){
                JOptionPane.showMessageDialog(null, "Missing something", "Error",JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            try {
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBookRoomTable(DefaultTableModel model){
        model.setRowCount(0);
        for (BookRoom br: bookRoomList){
            if (br.getCheckOut() == null){
                model.addRow(new Object[]{br.getBookingCode(), br.getEmployeeId(), br.getCustomerId(), br.getRoomId(), br.getCheckIn()});
            }else {
                model.addRow(new Object[]{br.getBookingCode(), br.getEmployeeId(), br.getCustomerId(), br.getRoomId(), br.getCheckIn(), br.getCheckOut()});
            }
        }
    }

    public void deleteBookRoom(String id){
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("delete from bookroom where booking_code = '" + id + "'");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchBookingCode(DefaultTableModel model, JTextField textField){
        List<BookRoom> searchList = new ArrayList<>();
        boolean check = false;
        for (BookRoom br: bookRoomList){
            if (br.getBookingCode().contains(textField.getText().toLowerCase()) && !textField.getText().equals("")){
                searchList.add(br);
                check = true;
            }
            else if (textField.getText().equals("")){
                check = false;
                addBookRoomTable(model);
            } else if (!check){
                model.setRowCount(0);
            }
        }
        if (check){
            model.setRowCount(0);
            for (BookRoom br: searchList) {
                model.addRow(new Object[]{br.getBookingCode(), br.getEmployeeId(), br.getCustomerId(), br.getRoomId(), br.getCheckIn(), br.getCheckOut()});
            }
        }
    }

    public void updateCheckOut(String date, String id){
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("update bookroom set check_out = '" + date + "' where booking_code = '" + id + "'");
            if (stm.executeUpdate() > 0)
                System.out.println("OK");
            else
                System.out.println("Error");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void roomAreUsing(){
        getBookRoomDB("select * from bookroom where check_out is null");
    }

    public void allRoom(){
        getBookRoomDB("select * from bookroom");
    }

    public List<BookRoom> getBookRoomList() {
        return bookRoomList;
    }
}
