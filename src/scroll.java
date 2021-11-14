import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class scroll extends JPanel implements ActionListener
{
    private Timer tmr;
    private int delay = 10;

    private int newX = 100;
    private int newY = 100;

    private int x;
    private int y;

    private JViewport v;

    private JButton jb;

    public scroll()
    {
        super(new BorderLayout());

        JScrollPane jsp = new JScrollPane(new JLabel(new ImageIcon("hotel.jpg")));
        v = jsp.getViewport();
        add(jsp);

        jb = new JButton("start");
        jb.addActionListener(this);
        add(jb, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e)
    {
// ... your custom event handling code here...

        if(e.getSource().equals(jb))
        {
            x = v.getViewPosition().x;
            y = v.getViewPosition().y;

            tmr = new Timer(delay, this);
            tmr.start();
        }

        if(e.getSource().equals(tmr))
        {
            if(x < newX)
            {
                x ++;
            }

            if(y < newY)
            {
                y ++;
            }

            if(!(x < newX) && !(y < newY))
            {
                tmr.stop();
            }

            v.setViewPosition(new Point(x, y));
        }
    }

    public static void main(String[] args)
    {
        JFrame jf = new JFrame("scrolltest");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setSize(200, 200);
        jf.setLocationRelativeTo(null);

        jf.getContentPane().add(new scroll());

        jf.setVisible(true);
    }
}