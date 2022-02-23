package entity;

import core.Main;
import core.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.World;
import terrain.*;

public class Sheep extends Entity
{
	protected int lastUpdateTime = 0;
	private int food = 100;
	private final int MAX_FOOD = 200;
	private final int EAT_VALUE = 20;
	private final int REPRODUCTION_REQ = 100;
	public boolean isValidTerrain(Terrain t)
	{
		return (t instanceof  Grass || t instanceof Ice) || (t instanceof Snow || t instanceof Sand || t instanceof Stone);
	}
	public int getFood()
	{
		return this.food;
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
		Tile destination = this.tile;
		int x = tile.getX();
		int y = tile.getY();
		double r = Math.random();

		if (r < .25 && x > 0)//West
		{
			destination = World.getTile(x - 1, y);
		}
		if((r >= 0.25 && r < 0.5) && x < World.getTilesHorizontal() - 1) //east
		{
			destination = World.getTile(x + 1, y);
		}
		if((r >=0.5 && r < 0.75) && y > 0) //north
		{
			destination = World.getTile(x, y - 1);
		}
		if(r >=0.75 && y < World.getTilesVertical() - 1)//south
		{
			destination = World.getTile(x, y+ 1);
		}
		if (canEnter(destination)) {
			tile.clearEntity();
			destination.setEntity(this);
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

		if (food > REPRODUCTION_REQ) {
			Tile destination = this.tile;
			double r = Math.random();

			if (r < .25 && x > 0)//West
			{
				destination = World.getTile(x - 1, y);
			}
			if((r >= 0.25 && r < 0.5) && x < World.getTilesHorizontal() - 1) //east
			{
				destination = World.getTile(x + 1, y);
			}
			if((r >=0.5 && r < 0.75) && y > 0) //north
			{
				destination = World.getTile(x, y - 1);
			}
			if(r >=0.75 && y < World.getTilesVertical() - 1)//south
			{
				destination = World.getTile(x, y+ 1);
			}
			if (canEnter(destination)) {
				destination.setEntity(new Sheep());
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
	public void escape(float x, float y)
	{

	}
}
