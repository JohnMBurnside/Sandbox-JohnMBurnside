import java.awt.Color;
import java.awt.Graphics;
public class OvalBalloon extends Balloon
{
    public OvalBalloon()
        { super(); }
    public OvalBalloon(int x, int y, int r, Color c)
        { super(x, y, r, c); }
    public double distance(int x, int y)
    {
        double dx = x - getX();
        double dy = y - getY();
        return Math.sqrt(4*dx*dx + dy*dy);
    }
    public void draw(Graphics g, boolean makeItFilled)
    {
        g.setColor(getColor());
        int r = getRadius();
        int x = getX();
        int y = getY();
        if(makeItFilled)
            g.fillOval(x - r/2, y - r, r, r*2);
        else
            g.drawOval(x - r/2, y - r, r, r*2);
    }
}