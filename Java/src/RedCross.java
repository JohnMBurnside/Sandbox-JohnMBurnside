// Chapter 2 exercise(debug the program)
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class RedCross extends JPanel
{
    // Paint Component Function
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;
        g.setColor(Color.RED);
        // Old Code
        // g.fillRect(xCenter, yCenter, 10, 50);
        // g.fillRect(xCenter, yCenter, 50, 10);
        // New Code
        g.fillRect(xCenter - 5, yCenter, 10, 50);
        g.fillRect(xCenter - 5, yCenter - 50, 10, 50);
        g.fillRect(xCenter - 50, yCenter - 5, 50, 10);
        g.fillRect(xCenter, yCenter - 5, 50, 10);
    }
    // Main Function
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Red Cross");
        window.setBounds(300, 300, 200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RedCross panel = new RedCross();
        panel.setBackground(Color.WHITE);
        Container c = window.getContentPane();
        c.add(panel);
        window.setVisible(true);
    }
}