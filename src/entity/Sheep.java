package entity;

import core.Main;
import core.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.World;
import terrain.Grass;
import terrain.Snow;
import terrain.Terrain;

public class Sheep extends Entity
{
	protected int lastUpdateTime = 0;

	public boolean isValidTerrain(Terrain t)
	{
		return t instanceof  Grass || t instanceof Snow;
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

		if (r < .25 && x > 0)//West
		{
			Tile destination = World.getTile(x - 1, y);
			if (canEnter(destination)) {
				destination.setEntity(this);
				return;
			}

		}
		if(r < 0.5 && x < World.getTilesHorizontal() - 1) //east
		{
			Tile destination = World.getTile(x + 1, y);
			if (canEnter(destination)) {
				destination.setEntity(this);
				return;
			}
		}
		if(r < 0.75 && y > 0) //north
		{
			Tile destination = World.getTile(x, y - 1);
			if (canEnter(destination)) {
				destination.setEntity(this);
				return;
			}
		}
		if(y < World.getTilesVertical() - 1)//south
		{
			Tile destination = World.getTile(x, y+ 1);
			if (canEnter(destination)) {
				destination.setEntity(this);
				return;
			}
		}
	}
}
