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
        g.setColor(new Color(height * 100, height * 100, height * 100));
        g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
    }
}
