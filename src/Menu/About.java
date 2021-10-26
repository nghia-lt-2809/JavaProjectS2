package Menu;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame{
    private JPanel aboutPanel;
    private JLabel titleLabel;
    private JPanel bodyPanel;
    private JLabel nameLabel1;
    private JLabel nameLabel2;
    private JLabel nameLabel3;
    private JLabel nameLabel4;
    private JLabel nameLabel5;
    private JLabel headingPanel;

    public About(){
        initUI();
    }

    private void initUI() {
        add(aboutPanel);
        setSize(500, 350);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
