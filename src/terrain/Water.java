package terrain;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.World;

public class Water extends Terrain
{
	private float depth;
	public Water(float depth)
	{
		this.depth = depth;
	}
	public void update() 
	{

	}

	public float getElevation()
	{
		return 0;
	}

	public void render(Graphics g)
	{

		g.setColor(new Color(depth/2, depth/2, 1.7f*depth));
		g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
	}

}
