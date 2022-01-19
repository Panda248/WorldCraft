package terrain;

import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Mountain extends Terrain
{


	private float elevation;
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
		int r = (int) (elevation * 225);

		int gr = (int) (elevation * 150);

		g.setColor(new Color(r, gr, 50));
		g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
	}

}
