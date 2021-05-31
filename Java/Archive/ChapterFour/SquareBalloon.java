import java.awt.Color;
import java.awt.Graphics;
public class SquareBalloon extends Balloon
{
    private int xCenter, yCenter, radius;
    private Color color;
    public SquareBalloon()
        { super(); }
    public SquareBalloon(int x, int y, int r, Color c)
        { super(x, y, r, c); }
    public double distance(int x, int y)
    {
        xCenter = getX();
        yCenter = getY();
        double dx = x - xCenter;
        double dy = y - yCenter;
        dx /= 2 * Math.sqrt(2);
        return Math.sqrt(dx * dx + dy * dy);
    }
    public void draw(Graphics g, boolean makeItFilled)
    {
        g.setColor(getColor());
        int x = getX();
        int y = getY();
        int r = getRadius();
        if (makeItFilled)
            g.fillRect(x - r, y - r, 2*r, 2*r);
        else
            g.drawRect(x - r, y - r, 2*r, 2*r);
    }
}