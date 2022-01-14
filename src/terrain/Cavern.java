package terrain;

import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Cavern extends Terrain
{

    public void update() {

    }

    public void render(Graphics g) {
        g.setColor(new Color(25,25,25));
        g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
    }
}
