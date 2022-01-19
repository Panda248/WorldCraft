package terrain;

import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Snow extends Terrain
{
    private float height;
    public float getElevation()
    {
        return height;
    }
    public Snow(float height)
    {
        this.height = height;
    }
    public void update()
    {

    }
    public void render(Graphics g)
    {
        int r = (int) (height * 225);

        int gr = (int) (height * 225);

        int b = (int) (height * 225);

        g.setColor(new Color(r, gr, b));
        g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
    }
}
