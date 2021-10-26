package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBar extends JFrame{
    private JMenuBar menuBar;
    private JMenu information,admin;
    private JMenuItem about, exit, system;

    public MenuBar(){
        initUI();
    }

    private void initUI() {
        ImageIcon icon = new ImageIcon("hotel.jpg");
        add(new JLabel(icon));
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
        system = new JMenuItem("System");
        system.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
            }
        });
        admin.add(system);
        pack();
        setSize(1200, 690);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
    }
}
