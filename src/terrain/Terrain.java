package terrain;

import org.newdawn.slick.Graphics;

import core.Tile;

abstract public class Terrain 
{
	protected Tile tile;
	
	public Terrain()
	{
		
	}
	
	public void setTile(Tile t)
	{
		tile = t;
	}
		
	abstract public void update();
	abstract public void render(Graphics g);
	
}
