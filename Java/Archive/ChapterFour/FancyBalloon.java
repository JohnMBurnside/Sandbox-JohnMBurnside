import java.awt.Color;
import java.awt.Graphics;
public class FancyBalloon extends Balloon
{
    private int xCenter, yCenter, radius;
    private Color color;
    public FancyBalloon()
        { super(); }
    public FancyBalloon(int x, int y, int r, Color c)
        { super(x, y, r, c); }
    public void draw(Graphics g, boolean makeItFilled)
    {
        g.setColor(getColor());
        int x = getX();
        int y = getY();
        int r = getRadius();
    
        if (makeItFilled)
            g.fillRoundRect(x - r,
                    y - r, 2*r, 2*r, r, r);
        else
            g.drawRoundRect(x - r,
                    y - r, 2*r, 2*r, r, r);
    }
}