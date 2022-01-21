package entity;

import core.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.World;
import terrain.Grass;
import terrain.Terrain;

public class Sheep extends Entity
{
	protected int lastUpdateTime = 0;

	public boolean isValidTerrain(Terrain t)
	{
		return t instanceof  Grass;
	}

	public void update() 
	{
		if(lastUpdateTime != World.getTime())
		{
			move();
		}
		lastUpdateTime = World.getTime();
	}

	public void render(Graphics g) 
	{
		g.setColor(new Color(255, 255, 255));
		g.fillOval(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
	}
	public void move()
	{
		int x = tile.getX();
		int y = tile.getY();
		double r = Math.random();


		try {
			if (r < .25 && x > 0)//West
			{
				Tile destination = World.getTile(x - 1, y);
				if (canEnter(destination)) {
					destination.setEntity(this);
				}
			}
			if (r < .75 && x > 5)//East
			{
				Tile destination = World.getTile(x + 1, y);
				if (canEnter(destination)) {
					destination.setEntity(this);
				}
			}
			if (r < .5 && x > 0.25 && World.getTile(x, y - 1) != null)//North
			{
				Tile destination = World.getTile(x, y - 1);
				if (canEnter(destination)) {
					destination.setEntity(this);
				}
			}
			if (x > 0.75)//South
			{
				Tile destination = World.getTile(x, y + 1);
				if (canEnter(destination)) {
					destination.setEntity(this);
				}
			}
		} catch(ArrayIndexOutOfBoundsException e)
		{

		}
	}
}
