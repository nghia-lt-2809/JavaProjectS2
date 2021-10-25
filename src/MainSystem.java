import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainSystem extends JFrame{
    private JPanel mainPanel;
    private JButton button1;
    private JMenuBar menuBar;
    private JMenu introduce;

    public MainSystem(){
        try {
            initUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUI() throws IOException {
        add(mainPanel);
//        menuBar = new JMenuBar();
//        introduce = new JMenu("Thông tin");
//        menuBar.add(introduce);
//        setJMenuBar(menuBar);
//        BufferedImage myPicture = ImageIO.read(new File("hotel.jpg"));
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        add(picLabel);
        button1.setBorderPainted(false);
        button1.setBorder(null);
        button1.setMargin(new Insets(0, 0, 0, 0));
        button1.setContentAreaFilled(false);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
