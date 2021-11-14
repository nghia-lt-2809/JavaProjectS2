package Menu;

import LoginForm.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JFrame{
    private JMenuBar menuBar;
    private JMenu information,admin;
    private JMenuItem about, exit, system, logout;
    private String employeeId;

    public MenuBar(){
        initUI();
    }

    private void initUI() {
        JLabel background = new JLabel(new ImageIcon("hotel.jpg"));
        background.setLayout(new BorderLayout());
        JLabel title = new JLabel("Hotel Management System", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(35,0,0,0));
        title.setVerticalAlignment(JLabel.TOP);
        title.setFont(title.getFont().deriveFont(50f));
        title.setForeground(Color.decode("#ffffff"));
        background.add(title);
        add(background);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        information = new JMenu("Information");
        menuBar.add(information);
        about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new About();
            }
        });
        information.add(about);
        information.addSeparator();
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        information.add(exit);

        admin = new JMenu("Admin");
        menuBar.add(admin);
        system = new JMenuItem("Main System");
        system.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainForm mainForm = new MainForm();
                mainForm.setEmployeeId(getEmployeeId());
                mainForm.setVisible(true);
            }
        });
        admin.add(system);
        admin.addSeparator();
        logout = new JMenuItem("Log out");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        admin.add(logout);
        setTitle("Hotel Management System");
        pack();
//        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
