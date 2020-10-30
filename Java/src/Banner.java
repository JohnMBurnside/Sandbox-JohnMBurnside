// Program that draws a banner that moves back and forth
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@SuppressWarnings("serial")
public class Banner extends JPanel implements ActionListener
{
    private int xPos, yPos;
    private String message = "East or West";
    private boolean messageID = true;
    private int time = 0;
    // Paint Component Function
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // Paint the background
        g.setColor(Color.BLACK);
        g.fillRect(xPos, yPos - yPos/2, 100, 25);
        g.setColor(Color.WHITE);
        g.drawString(message, xPos + 15, yPos);
    }
    // Action Performed Function
    public void actionPerformed(ActionEvent e)
    {
        xPos++; 
        if (xPos > getWidth())
            xPos = -100;
        if(time >= 66)
        {
            if(messageID)
            {
                message = "Java is Best";
                messageID = false;
            }
            else if(!messageID)
            {
                message = "East or West";
                messageID = true;
            }   
            time = 0;
        }
        else
            time++;
        repaint();
    }
    // Main Function
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Action Demo");
        window.setBounds(300, 300, 300, 100);
        Banner panel = new Banner();
        panel.setBackground(Color.CYAN);
        Container c = window.getContentPane();
        c.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        panel.xPos = panel.getWidth();
        panel.yPos = panel.getHeight() / 2;
        Timer clock = new Timer(30, panel);
        clock.start();
    }
}