package core;

import org.newdawn.slick.Graphics;

import entity.Entity;
import terrain.Terrain;

public class Tile 
{
	/****************************** DATA ******************************/
	
	protected Entity entity;
	protected Terrain terrain;
	protected int x;
	protected int y;
	
	/****************************** CORE METHODS ******************************/
	
	public Tile(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	

	public void update()
	{
		terrain.update();
		
		if(hasEntity())
		{
			entity.update();
		}
	}
	
	public void render(Graphics g) 
	{
		terrain.render(g);
		
		if(hasEntity())
		{
			entity.render(g);
		}
	}
	
	/****************************** ACCESSORS ******************************/
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean hasEntity()
	{
		return entity != null;
	}
	
	/****************************** MUTATORS ******************************/
	
	public void setEntity(Entity e)
	{
		entity = e;
		e.setTile(this);
	}

	public void clearEntity()
	{
		entity = null;
	}

	public Entity getEntity()
	{
		return this.entity;
	}

	public void setTerrain(Terrain t)
	{
		terrain = t;
		t.setTile(this);
	}

	public Terrain getTerrain()
	{
		return this.terrain;
	}
}
