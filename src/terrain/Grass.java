package terrain;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.World;

public class Grass extends Terrain
{
	final private float HIGHEST_GRASS = 100;
	final private float HIGHEST_GROWTH_RATE = 0.2f;
	private float curGrass; //units of grass current tile has
	private float maxGrass;
	private float growthRate;

	public float getGrass()
	{
		return curGrass;
	}

	public void removeGrass(int amount)
	{
		curGrass -= amount;
	}

	public float percentGrass()
	{
		return curGrass / HIGHEST_GRASS;
	}
	public Grass(float fertility)
	{
		maxGrass = (float) (HIGHEST_GRASS * fertility);
		growthRate = (float) (HIGHEST_GROWTH_RATE * fertility);
		curGrass = (float) (maxGrass * Math.random());
	}
	public void update() 
	{
		if(curGrass < maxGrass)
		{
			curGrass += growthRate;
		}
	}

	public void render(Graphics g)
	{
		int red = (int) (50 * (1 - percentGrass()));
		int green = (int) (150 * percentGrass()) + 50;
		int blue = 0;
		g.setColor(new Color(red, green, blue));
		g.fillRect(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
	}
}
