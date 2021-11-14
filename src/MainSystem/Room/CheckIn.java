package MainSystem.Room;

import MainSystem.Customer.CustomerDB;
import MainSystem.GenerateRandom;
import MainSystem.ResizeColumn;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CheckIn extends JFrame{
    private JPanel roomBookingPanel;
    private JTable customerTable;
    private JTable roomTable;
    private JTable rentTable;
    private JButton addButton;
    private JButton updateCustomerButton;
    private JButton updateRoomButton;
    private JButton checkInButton;
    private JButton backButton;
    private JButton deleteButton;
    private JTextField searchCustomerNameField;
    private JTextField searchRoomTypeField;
    private JTextField bookCodeField;
    private JTextField customerIdField;
    private JTextField checkInField;
    private JTextField priceField;
    private JTextField roomIdField;
    private JTextField employeeIdField;

    public CheckIn(){
        initUI();
    }

    private void initUI() {
        CustomerTable();
        RoomTable();
        RentTable();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        checkInField.setText(formatter.format(date));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(roomBookingPanel);
        setSize(800, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void CustomerTable(){
        String[] column = {"Customer ID", "Name", "Phone number", "Identity card", "Address"};
        CustomerDB customer = new CustomerDB();
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (String s : column) {
            model.addColumn(s);
        }
        customerTable.setModel(model);
        customerTable.setFillsViewportHeight(true);
        new ResizeColumn().resizeColumnsWidth(customerTable);
        customer.customerNotBookRoom();
        customer.addCustomerTable(model);
        searchCustomerNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                customer.searchCustomer(model, searchCustomerNameField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                customer.searchCustomer(model, searchCustomerNameField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                customer.searchCustomer(model, searchCustomerNameField);
            }
        });
        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roomTable.getSelectionModel().clearSelection();
                rentTable.getSelectionModel().clearSelection();
                int selectRowIndex = customerTable.getSelectedRow();
                customerIdField.setText(model.getValueAt(selectRowIndex, 0).toString());
            }
        });
    }

    private void RoomTable(){
        String[] column = {"Room ID", "Type", "Price", "Status"};
        RoomDB room = new RoomDB();
        room.roomAreRenting();
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (String s : column) {
            model.addColumn(s);
        }
        roomTable.setModel(model);
        roomTable.setFillsViewportHeight(true);
        new ResizeColumn().resizeColumnsWidth(roomTable);
        room.addRoomTable(model);
        searchRoomTypeField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                room.searchRoomByName(model, searchRoomTypeField);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                room.searchRoomByName(model, searchRoomTypeField);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                room.searchRoomByName(model, searchRoomTypeField);
            }
        });
        roomTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectRowIndex = roomTable.getSelectedRow();
                customerTable.getSelectionModel().clearSelection();
                rentTable.getSelectionModel().clearSelection();
                roomIdField.setText(model.getValueAt(selectRowIndex, 0).toString());
                priceField.setText(model.getValueAt(selectRowIndex, 2).toString());
            }
        });
    }

    private void RentTable(){
        String[] column = {"Booking code", "Employee ID", "Customer ID", "Room ID", "Check in"};
        BookRoomDB bookRoomDB = new BookRoomDB();
        GenerateRandom random = new GenerateRandom();
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (String s : column) {
            model.addColumn(s);
        }
        rentTable.setModel(model);
        rentTable.setFillsViewportHeight(true);
        new ResizeColumn().resizeColumnsWidth(rentTable);
        bookRoomDB.roomAreUsing();
        bookRoomDB.addBookRoomTable(model);
        bookCodeField.setText(random.randomBookingCode(10));
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bookRoomDB.setBookRoomDB(bookCodeField.getText(), customerIdField.getText(),
                            checkInField.getText(), roomIdField.getText(), employeeIdField.getText());
                    customerIdField.setText("");
                    roomIdField.setText("");
                    priceField.setText("");
                    bookCodeField.setText(random.randomBookingCode(10));
                    bookRoomDB.roomAreUsing();
                    bookRoomDB.addBookRoomTable(model);
                    RoomTable();
                    CustomerTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectRow = rentTable.getSelectedRow();
                String code = (String) model.getValueAt(selectRow, 0);
                model.removeRow(selectRow);
                bookRoomDB.deleteBookRoom(code);
                CustomerTable();
                RoomTable();
            }
        });
        rentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roomTable.getSelectionModel().clearSelection();
                customerTable.getSelectionModel().clearSelection();
            }
        });
    }

    public void setEmployeeIdField(String id){
        employeeIdField.setText(id);
    }
}
