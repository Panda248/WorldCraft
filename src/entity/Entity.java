package entity;

import org.newdawn.slick.Graphics;

import core.Tile;
import terrain.Terrain;

abstract public class Entity
{
	protected Tile tile;
	
	public Entity()
	{

	}
	
	public void setTile(Tile t)
	{
		tile = t;
	}

	public boolean canEnter(Tile t)
	{
		return !t.hasEntity() && isValidTerrain(t.getTerrain());
	}
	abstract public boolean isValidTerrain(Terrain t);
	abstract public void update();
	abstract public void render(Graphics g);
}
