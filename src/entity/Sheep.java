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
	private int food = 10;
	private final int MAX_FOOD = 10;
	private final int EAT_VALUE = 20;
	public boolean isValidTerrain(Terrain t)
	{
		return t instanceof  Grass || t instanceof Snow;
	}

	public void update() 
	{
		if(lastUpdateTime != World.getTime())
		{
			hunger();
			eat();
			reproduce();
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
				tile.clearEntity();
				destination.setEntity(this);
				return;
			}

		}
		if(r < 0.5 && x < World.getTilesHorizontal() - 1) //east
		{
			Tile destination = World.getTile(x + 1, y);
			if (canEnter(destination)) {
				tile.clearEntity();
				destination.setEntity(this);
				return;
			}
		}
		if(r < 0.75 && y > 0) //north
		{
			Tile destination = World.getTile(x, y - 1);
			if (canEnter(destination)) {
				tile.clearEntity();
				destination.setEntity(this);
				return;
			}
		}
		if(y < World.getTilesVertical() - 1)//south
		{
			Tile destination = World.getTile(x, y+ 1);
			if (canEnter(destination)) {
				tile.clearEntity();
				destination.setEntity(this);
				return;
			}
		}
	}

	public void eat()
	{
		if(tile.getTerrain() instanceof Grass && food <= MAX_FOOD)
		{
			// Since we know the Terrain is a grass, we'll cast it to a grass
			// This lets us use the handy new grass methods
			Grass grassTile = (Grass)(tile.getTerrain());

			if(grassTile.getGrass() > EAT_VALUE)
			{
				grassTile.removeGrass(EAT_VALUE);
				food += EAT_VALUE/10;
			}
		}
	}

	public void reproduce() {
		int x = tile.getX();
		int y = tile.getY();

		if (food > 10) {
			if (x > 0) {
				Tile babyTile = World.getTile(x - 1, y);
				babyTile.setEntity(new Sheep());
				food -= 5;
			}
		}
	}
	public void hunger()
	{
		if(food > 0)
		{
			food--;
		}
		else
		{
			tile.clearEntity();
		}
	}
}
