package MainSystem.Customer;

import MainSystem.ResizeColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerInfo extends JFrame{
    private JTable customerTable;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addButton;
    private JButton backButton;
    private JPanel customerPanel;
    Customer newCustomer = new Customer();

    public CustomerInfo(){
        initUI();
    }

    private void initUI() {
        CustomerTable();
        add(customerPanel);
        setTitle("Login");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void CustomerTable(){
        String[] column = {"Customer ID", "Name", "Phone number", "Identity card", "Address"};
        CustomerDB customer = new CustomerDB();
        customer.getAllData();
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
        customer.addCustomerTable(model);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCustomer update = new UpdateCustomer();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateCustomer().setCustomer(newCustomer);
            }
        });

        customerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectRowIndex = customerTable.getSelectedRow();
                Customer customer1 = new Customer();
                customer1.setId(model.getValueAt(selectRowIndex, 0).toString());
                customer1.setName(model.getValueAt(selectRowIndex, 1).toString());
                customer1.setPhoneNumber(model.getValueAt(selectRowIndex, 2).toString());
                customer1.setIdentityCard(model.getValueAt(selectRowIndex, 3).toString());
                customer1.setAddress(model.getValueAt(selectRowIndex, 4).toString());
                newCustomer = customer1;
            }
        });
    }

    public TableModel getTableModel(){
        return customerTable.getModel();
    }
}
