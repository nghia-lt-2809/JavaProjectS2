package Menu;

import MainSystem.Room.CheckOut;
import MainSystem.Room.FindRoom;
import MainSystem.Room.CheckIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame{
    private JButton customerInforButton;
    private JButton roomsButton;
    private JButton bookingDiaryButton;
    private JButton serviceButton;
    private JButton checkoutAndPaymentButton;
    private JButton searchForCustomerInfoButton;
    private JButton searchForRoomButton;
    private JButton logBookButton;
    private JButton checkinButton;
    private JPanel mainPanel;
    private JPanel imagePanel;
    private String employeeId;

    public MainForm(){
        initUI();
        searchForRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindRoom();
            }
        });
        checkinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckIn roomBooking = new CheckIn();
                roomBooking.setEmployeeIdField(getEmployeeId());
                roomBooking.setVisible(true);
            }
        });
        checkoutAndPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckOut().setVisible(true);
            }
        });
    }

    private void initUI() {
        add(mainPanel);
        setSize(730, 527);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
