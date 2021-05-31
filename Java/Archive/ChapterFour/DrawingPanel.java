// Drawing Panel
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
@SuppressWarnings("serial")
public class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
    private ArrayList<Balloon> balloons;
    private Balloon activeBalloon;
    private Color color;
    private boolean picked, stretching;
    private int offsetX, offsetY;
    private double offsetR;
    // Constructor
    public DrawingPanel()
    {
        setBackground(Color.WHITE);

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        balloons = new ArrayList<Balloon>();
        activeBalloon = null;
        picked = false;
        stretching = false;

        color = Color.BLUE;
    }
    // Pick Color Method: Called from controlPanel when the "Pick color" button is clicked.
    public void pickColor()
    {
        Color pickedColor = JColorChooser.showDialog(this, "Pick a color", color);
        if (pickedColor != null)
            color = pickedColor;
    }
    // Get Color Method: Returns the current color.
    public Color getColor()
        { return color; }
    // Add Balloon Method
    public void addBalloon(int shape)
    {
        int w = getWidth();
        int h = getHeight();
        int radius = 10 + (int)(Math.random() * w / 2);
        switch (shape)
        {
            case 1: activeBalloon = new RoundBalloon(w/2, h/2, radius, color); break;
            case 2: activeBalloon = new OvalBalloon(w/2, h/2, radius, color); break;
            case 3: activeBalloon = new SquareBalloon(w/2, h/2, radius, color); break;
            case 4: activeBalloon = new FancyBalloon(w/2, h/2, radius, color); break;
            default: activeBalloon = new RoundBalloon(w/2, h/2, radius, color); break;
        }
        balloons.add(activeBalloon);
        repaint();
    }
    // Paint Component Method: Repaints this panel. If activeBalloon is set, paints it on top.
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Balloon b : balloons)
        {
            if (!picked || b != activeBalloon)
            b.draw(g, true);
        }
        if (picked && activeBalloon != null)
            activeBalloon.draw(g, false);
    }
    // Mouse Pressed Method
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        picked = false;
        for (int k = balloons.size() - 1; k >= 0 && !picked; k--)
        {
            Balloon b = balloons.get(k);
            if (b.isInside(x, y))
            {
                picked = true;
                offsetX = x - b.getX();
                offsetY = y - b.getY();
                stretching = false;
                activeBalloon = b;
            }
            else if (b.isOnBorder(x, y))
            {
                picked = true;
                offsetR = b.distance(x, y) - b.getRadius();
                stretching = true;
                activeBalloon = b;
            }
        }
        if (picked)
            repaint();
    }
    // Mouse Released Method
    public void mouseReleased(MouseEvent e)
    {
        if (picked)
            repaint();
        picked = false;
        stretching = false;
    }
    // Mouse Dragged Method
    public void mouseDragged(MouseEvent e)
    {
        if (activeBalloon == null || !picked)
            return;
        int x = e.getX();
        int y = e.getY();
        if (stretching)
            activeBalloon.setRadius((int)(Math.round(activeBalloon.distance(x, y) - offsetR)));
        else
            activeBalloon.move(x - offsetX, y - offsetY);
        repaint();
    }
    // Key Pressed Method
    public void keyPressed (KeyEvent e)
    {
        if (activeBalloon == null)
            return;
        int dx = 0, dy = 0, dr = 0;
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                dy = -1;
                dr = 1;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                dy = 1;
                dr = -1;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
                dx = -1;
                dr = -1;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
                dx = 1;
                dr = 1;
                break;
        }
        if (dx != 0 || dy != 0 || dr != 0)
        {
            if (e.isControlDown())
                activeBalloon.setRadius(activeBalloon.getRadius() + dr);
            else
                activeBalloon.move(activeBalloon.getX() + dx, activeBalloon.getY() + dy);
            repaint();
        }
    }
    // Not used:
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}
