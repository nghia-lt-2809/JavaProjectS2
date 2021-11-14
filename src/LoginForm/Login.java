package LoginForm;

import Menu.MenuBar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField userField;
    private JPanel loginPanel;
    private JTextField passwordField;
    private JButton loginButton;
    private JTextField idField;
    private JButton exitButton;

    public Login(){
        LoginDB admin = new LoginDB();
        initUI();
        getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (admin.login(idField, userField, passwordField)){
                    MenuBar menuBar = new MenuBar();
                    menuBar.setEmployeeId(idField.getText());
                    menuBar.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Something's wrong", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initUI(){
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(loginPanel);
        setTitle("Login");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

