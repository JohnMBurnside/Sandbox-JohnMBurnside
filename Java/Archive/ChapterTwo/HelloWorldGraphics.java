// (Question 16) Program that draws graphics
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class HelloWorldGraphics extends JPanel
{
    // Paint Component Function
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(20, 40, 150, 45);
        g.setColor(Color.BLUE);
        g.fillRect(20, 40, 150, 45);
        g.setColor(Color.WHITE);
        g.drawString("Hello World!", 55, 65);
    }
    // Main Function
    public static void main(String[] args)
    {
        JFrame window = new JFrame("Graphics Demo");
        window.setBounds(300, 300, 200, 150);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HelloWorldGraphics panel = new HelloWorldGraphics();
        panel.setBackground(Color.WHITE);
        Container c = window.getContentPane();
        c.add(panel);
        window.setVisible(true);
    }
}
