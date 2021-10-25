import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JTextField userField;
    private JPanel loginPanel;
    private JTextField passwordField;
    private JButton loginButton;
    private JTextField idField;
    private JButton exitButton;

    public Login(){
        initUI();
        getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (login()){
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                    dispose();
                    new MainSystem();
                } else {
                    JOptionPane.showMessageDialog(null, "Không thành công");
                }
            }
        });

    }

    private void initUI(){
        add(loginPanel);
        setTitle("Login");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private boolean login(){
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("select * from employee");
            rs = stm.executeQuery();
            while (rs.next()){
                String ID = rs.getString("id");
                String userName = rs.getString("user_name");
                String password = rs.getString("password");
                check = ID.equals(idField.getText()) && userName.equals(userField.getText()) && password.equals(passwordField.getText());
                if (check)
                    break;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            try{
                if (rs != null)
                    rs.close();
                if (stm != null)
                    stm.close();
                if (con != null)
                    con.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return check;
    }
}
