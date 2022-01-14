package terrain;

import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Mountain extends Terrain
{


	private static float elevation;
	public float getElevation()
	{
		return elevation;
	}
	public Mountain(float elevation)
	{
		this.elevation = elevation;
	}
	public void update() 
	{

	}

	public void render(Graphics g)
	{
		int r = (int) (elevation * 100);

		int gr = (int) (elevation * 100);

		int b = (int) (elevation * 100);

		g.setColor(new Color(r, gr, b));
		g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
	}

}
