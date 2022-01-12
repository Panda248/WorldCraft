package terrain;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.World;

public class Water extends Terrain
{
	private final float MAX_DEPTH = 100;
	private float depth;
	public Water(float distance)
	{
		depth = (float) MAX_DEPTH * distance;
	}

	public void update() 
	{

	}

	public void render(Graphics g)
	{
		int red = (int) ((1 - depth)*50);
		int blue = (int) (depth * 150) + 50;
		int green = (int) ((1 - depth)*50);
		g.setColor(new Color(red, green, blue));
		g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
	}

}
