package Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainForm extends JFrame{
    private JButton customerInforButton;
    private JButton roomsButton;
    private JButton bookingDiaryButton;
    private JButton serviceButton;
    private JButton checkoutAndPaymentButton;
    private JButton searchForCustomerInfoButton;
    private JButton searchForRoomButton;
    private JButton logBookButton;
    private JPanel mainPanel;
    private JPanel imagePanel;

    public MainForm(){
        initUI();
    }

    private void initUI() {
        add(mainPanel);
        setSize(730, 527);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
