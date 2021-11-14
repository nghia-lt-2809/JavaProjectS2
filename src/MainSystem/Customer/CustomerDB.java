package MainSystem.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
    private List<Customer> customerList = new ArrayList<>();

    private void getCustomerDB(String search){
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        customerList.clear();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement(search);
            rs = stm.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getString("c_id"));
                customer.setName(rs.getString("c_name"));
                customer.setPhoneNumber(rs.getString("c_phonenumber"));
                customer.setIdentityCard(rs.getString("c_identitycard"));
                customer.setAddress(rs.getString("c_address"));
                customerList.add(customer);
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

    public void setCustomerDB(Customer customer){
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("insert into customer(c_id, c_name, c_identitycard, c_address, c_phonenumber) " +
                    "VALUES (?,?,?,?,?)");
            stm.setString(1, customer.getId());
            stm.setString(2, customer.getName());
            stm.setString(3, customer.getIdentityCard());
            stm.setString(4, customer.getAddress());
            stm.setString(5, customer.getPhoneNumber());
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

    public void addCustomerTable(DefaultTableModel model){
        model.setRowCount(0);
        for (Customer c: customerList) {
            model.addRow(new Object[]{c.getId(), c.getName(), c.getPhoneNumber(), c.getIdentityCard(), c.getAddress()});
        }
    }

    public void searchCustomer(DefaultTableModel model, JTextField textField){
        List<Customer> searchList = new ArrayList<>();
        boolean check = false;
        for (Customer c: customerList){
            if (c.getName().toLowerCase().contains(textField.getText().toLowerCase()) && !textField.getText().equals("")){
                searchList.add(c);
                check = true;
            }
            else if (textField.getText().equals("")){
                check = false;
                addCustomerTable(model);
            } else if (!check){
                model.setRowCount(0);
            }
        }
        if (check){
            model.setRowCount(0);
            for (Customer c: searchList) {
                model.addRow(new Object[]{c.getId(), c.getName(), c.getPhoneNumber(), c.getIdentityCard(), c.getAddress()});
            }
        }
    }

    public void getSomeData(String id, JTextField nameField, JTextField phoneField){
        for (Customer c: customerList){
            if (c.getId().equals(id)){
                nameField.setText(c.getName());
                phoneField.setText(c.getPhoneNumber());
                break;
            }
        }
    }

    public void customerNotBookRoom(){
        getCustomerDB("select * from customer where c_status = 0");
    }

    public void customerBookedRoom(){
        getCustomerDB("select * from customer where c_status = 1");
    }

    public void getAllData(){
        getCustomerDB("select * from customer");
    }

    public List<Customer> getCustomerList(){
        return customerList;
    }
}
