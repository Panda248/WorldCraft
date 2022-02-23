package terrain;

import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Stone extends Terrain{
    private float elevation;
    public float getElevation()
    {
        return elevation;
    }
    public Stone(float elevation)
    {
        this.elevation = elevation;
    }
    public void update()
    {

    }

    public void render(Graphics g)
    {
        g.setColor(new Color(elevation/2, elevation/2, elevation/2));
        g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
    }
}
