package entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.World;

public class Sheep extends Entity
{	
	public void update() 
	{
		
	}

	public void render(Graphics g) 
	{
		g.setColor(new Color(255, 255, 255));
		g.fillOval(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
	}

}
