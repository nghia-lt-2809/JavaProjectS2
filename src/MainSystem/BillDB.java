package MainSystem;

import MainSystem.Room.BookRoom;
import MainSystem.Room.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDB {
    private List<Bill> billList = new ArrayList<>();

    public void getBillDB() {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        billList.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("select * from bill where status = 0 order by timestamp");
            rs = stm.executeQuery();
            while (rs.next()){
                Bill bill = new Bill();
                bill.setBillId(rs.getString("bill_id"));
                bill.setBookingCode(rs.getString("booking_code"));
                bill.setEmployeeId(rs.getString("employee_id"));
                bill.setCustomerId(rs.getString("customer_id"));
                bill.setCustomerName(rs.getString("customer_name"));
                bill.setCustomerPhone(rs.getString("customer_phone"));
                bill.setRoomId(rs.getString("room_id"));
                bill.setCheckIn(rs.getString("check_in"));
                bill.setCheckOut(rs.getString("check_out"));
                bill.setTotalDay(rs.getInt("total_day"));
                bill.setTotalPrice(rs.getDouble("total_price"));
                bill.setStatus(rs.getInt("status"));
                billList.add(bill);
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

    public void setBillDB(Bill bill) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("insert into bill(bill_id, booking_code, employee_id, customer_id, customer_name, customer_phone, room_id, check_in, check_out, total_day, total_price) " +
                    "values " +
                    "(?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, bill.getBillId());
            stm.setString(2, bill.getBookingCode());
            stm.setString(3, bill.getEmployeeId());
            stm.setString(4, bill.getCustomerId());
            stm.setString(5, bill.getCustomerName());
            stm.setString(6, bill.getCustomerPhone());
            stm.setString(7, bill.getRoomId());
            stm.setString(8, bill.getCheckIn());
            stm.setString(9, bill.getCheckOut());
            stm.setLong(10, bill.getTotalDay());
            stm.setDouble(11, bill.getTotalPrice());
            if (stm.executeUpdate() > 0){
                System.out.println("OK");
            } else {
                System.out.println("Error");
            }
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

    public void addBillTable(DefaultTableModel model){
        model.setRowCount(0);
        NumberFormat formatter = new DecimalFormat("#,000");
        for (Bill b: billList) {
            model.addRow(new Object[]{b.getBillId(), b.getBookingCode(), b.getEmployeeId(), b.getCustomerId(),
            b.getCustomerName(), b.getCustomerPhone(), b.getRoomId(), b.getCheckIn(), b.getCheckOut(),
            b.getTotalDay(), formatter.format(b.getTotalPrice()), b.getStatus() == 0 ? "Unpaid" : "Paid"});
        }
    }

    public void updateStatus(String id){
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("update bill set status = 1 where bill_id = '" + id + "'");
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

    public List<Bill> getBillList() {
        return billList;
    }
}
