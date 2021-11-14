package MainSystem.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCustomer extends JDialog{
    private JTextField idField;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField cardField;
    private JTextField addressField;
    private JPanel updatePanel;
    private JButton addButton;

    public UpdateCustomer(){
        initUI();
    }

    private void initUI() {
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                CustomerDB customerDB = new CustomerDB();
//                customerDB.setCustomerDB(idField.getText(), nameField.getText(), cardField.getText(), addressField.getText(), phoneField.getText());
//
//            }
//        });
        add(updatePanel);
        setTitle("Login");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void setCustomer(Customer customer) {
        idField.setText(customer.getId());
        nameField.setText(customer.getName());
        phoneField.setText(customer.getPhoneNumber());
        cardField.setText(customer.getIdentityCard());
        addressField.setText(customer.getAddress());
    }
}
