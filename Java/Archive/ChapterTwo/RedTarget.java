// (Question 15 b) Program that draws a target made up of three ovals
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class RedTarget extends JPanel
{
    // Paint Component Function
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;
        g.setColor(Color.RED);
        g.fillOval(xCenter - 30, yCenter -30, 60, 60);
        g.setColor(Color.WHITE);
        g.fillOval(xCenter - 20, yCenter -20, 40, 40);
        g.setColor(Color.RED);
        g.fillOval(xCenter - 9, yCenter - 9, 18, 18);
    }
    // Main Function
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Red Target");
        window.setBounds(300, 300, 200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RedTarget panel = new RedTarget();
        panel.setBackground(Color.WHITE);
        Container c = window.getContentPane();
        c.add(panel);
        window.setVisible(true);
    }
}