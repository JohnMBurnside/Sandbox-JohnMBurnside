// Balloon Super Class
import java.awt.Color;
import java.awt.Graphics;
public class Balloon
{
    private int xCenter, yCenter, radius;
    private Color color;
    public Balloon()
    {
        xCenter = 0;
        yCenter = 0;
        radius = 50;
        color = Color.BLUE;
    }
    public Balloon(int x, int y, int r, Color c)
    {
        xCenter = x;
        yCenter = y;
        radius = r;
        color = c;
    }
    // Get X Method
    public int getX()
        { return xCenter; }
    // Get Y Method
    public int getY()
        { return yCenter; }
    // Get Radius Method
    public int getRadius()
        { return radius; }
    // Get Color Method
    public Color getColor()
        { return color; }
    public double distance(int x, int y)
    {
        double dx = x - xCenter;
        double dy = y - yCenter;
        return Math.sqrt(dx*dx + dy*dy);
    }
    public void move(int x, int y)
    {
        xCenter = x;
        yCenter = y;
    }
    // Set Radius Method
    public void setRadius(int r)
        { radius = r; }
    public boolean isInside(int x, int y)
        { return distance(x, y) < 0.9 * radius; }
    public boolean isOnBorder(int x, int y)
    {
        double d = distance(x, y);
        return d >= 0.9 * radius && d <= 1.1 * radius;
    }
    // Draw Method
    public void draw(Graphics g, boolean makeItFilled)
    {
        g.setColor(color);
        if (makeItFilled)
            g.fillOval(xCenter - radius, yCenter - radius, 2*radius, 2*radius);
        else
            g.drawOval(xCenter - radius, yCenter - radius, 2*radius, 2*radius);
    }
}