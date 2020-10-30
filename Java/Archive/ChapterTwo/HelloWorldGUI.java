// Program that creates a GUI that prints Hello World!
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.FlowLayout;
@SuppressWarnings("serial")
public class HelloWorldGUI extends JFrame
{
    // Constructor
    public HelloWorldGUI()
    {
        super("GUI Demo");
        Container c = getContentPane();
        c.setBackground(Color.cyan);
        c.setLayout(new FlowLayout());
        c.add(new JLabel(" Hello World!!", 10));
    }
    // Main Function
    public static void main(String[] args)
    {
        HelloWorldGUI window = new HelloWorldGUI();
        window.setBounds(300, 300, 200, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
