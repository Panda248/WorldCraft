package entity;

import org.newdawn.slick.Graphics;

import core.Tile;

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
	
	abstract public void update();
	abstract public void render(Graphics g);
}
