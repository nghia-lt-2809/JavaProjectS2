package LoginForm;

import javax.swing.*;
import java.sql.*;

public class LoginDB {
    private String empId;

    public boolean login(JTextField idField, JTextField userField, JTextField passwordField){
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
            stm = con.prepareStatement("select * from admin");
            rs = stm.executeQuery();
            while (rs.next()){
                String id = rs.getString("id");
                String username = rs.getString("user_name");
                String password = rs.getString("password");
                check = id.equals(idField.getText()) && username.equals(userField.getText()) && password.equals(passwordField.getText());
                if (check) {
                    empId = id;
                    break;
                }
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
        return check;
    }

    public String getEmpId() {
        return empId;
    }
}
