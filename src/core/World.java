package core;

import org.newdawn.slick.Graphics;

import entity.*;
import noise.PerlinNoise;
import terrain.*;

public class World 
{
	/****************************** DATA ******************************/

	public static int TILE_SIZE = 5;
	public static int NUM_SHEEP = 0;

	private Tile[][] tiles;
	
	/****************************** CORE METHODS ******************************/

	public World()
	{ 
		tiles = new Tile[getTilesHorizontal()][getTilesVertical()];
		generateWorld();
	}
	
	public void update()
	{
		// Update each tile
		for(int i = 0; i < getTilesHorizontal(); i++)
		{
			for(int j = 0; j < getTilesVertical(); j++)
			{
				tiles[i][j].update();
			}
		}
	}
	
	public void render(Graphics g)
	{
		// Render each tile
		for(int i = 0; i < getTilesHorizontal(); i++)
		{
			for(int j = 0; j < getTilesVertical(); j++)
			{
				tiles[i][j].render(g);
			}
		}
	}
	
	/****************************** ACCESSORS ******************************/

	
	public int getTilesHorizontal()
	{
		return Main.getScreenWidth() / TILE_SIZE;
	}
	
	public int getTilesVertical()
	{
		return Main.getScreenHeight() / TILE_SIZE;
	}
	
	/****************************** MUTATORS ******************************/
		
	public void generateWorld()
	{
		// Create the geography of the world
		for(int i = 0; i < getTilesHorizontal(); i++)
		{
			for(int j = 0; j < getTilesVertical(); j++)
			{
				tiles[i][j] = new Tile(i, j);
				//generateTileBasic(i, j);
				generateTileNoise(i, j);
			}
		}
		
		// Create the creatures living in the world
		generateEntities();
	}
	
	/*public void generateTileBasic(int x, int y)
	{		
		if(Math.random() < .7)					// 70% Chance to make a Grass Tile
		{
			tiles[x][y].setTerrain(new Grass());
		}			
		else									// 30% Chance to make a Water Tile
		{
			tiles[x][y].setTerrain(new Water());
		}
	}*/
	
	public void generateTileNoise(int x, int y)
	{
		float SCALE = .05f;
		float noise = (float) PerlinNoise.noise(x*SCALE, y*SCALE);
		if(noise < 0.3f)
		{
			tiles[x][y].setTerrain(new Water(noise));

		}			
		else if(noise < 0.6f)
		{
			tiles[x][y].setTerrain(new Grass(noise));
		}
		else
		{
			if(noise < 0.75f)
			{
				tiles[x][y].setTerrain(new Mountain(noise));
			}
			else
			{
				tiles[x][y].setTerrain(new Snow(4));
			}
		}
		noise = (float) PerlinNoise.noise(x * (SCALE*3), y * (SCALE*3));
		if(noise < 0.3f)
		{
			tiles[x][y].setTerrain(new Cavern());
		}
		noise = (float) PerlinNoise.noise(x * SCALE/2, y * SCALE/2);
		if(noise < 0.4)
		{
			if(tiles[x][y].terrain.getTerrain().equalsIgnoreCase("Water"))
			{
				System.out.println("ICE");
				tiles[x][y].setTerrain(new Ice());
			}
			else
			{
				//if(!tiles[x][y].terrain.getTerrain().equalsIgnoreCase("cavern"))
				//{
					//tiles[x][y].setTerrain(new Snow());
				//}
			}

		}
	}

	public void generateEntities()
	{
		for(int i = 0; i < NUM_SHEEP; i++)
		{
			addEntityRandomly(new Sheep());
		}
	}
		
	public void addEntityRandomly(Entity e)
	{
		// Select a random tile in the grid
		int rX = (int) (Math.random() * getTilesHorizontal());
		int rY = (int) (Math.random() * getTilesVertical());
		
		// If it is occupied, try again
		if(tiles[rX][rY].hasEntity())
		{
			addEntityRandomly(e);
		}
		
		// If it is not occupied, assign it to a tile
		else
		{
			tiles[rX][rY].setEntity(e);
		}
			
	}
	
	
	

}
