package core;

import org.newdawn.slick.Graphics;

import entity.*;
import noise.PerlinNoise;
import terrain.*;

public class World 
{
	/****************************** DATA ******************************/

	public static int TILE_SIZE = 5;
	public static int NUM_SHEEP = 100; //Sheep: Eats grass and reproduces when grass eaten reaches a threshold
	public static int NUM_WOLVES = 100;//Wolves: Eats Sheep and reproduces when sheep eaten reaches a threshold
	public static int NUM_SWOLVES = 100;//Smart Wolves/S-Wolves: Pathfinds (lmao not really) to sheep to eat. Same as normal wolf.
	public static int NUM_GOLEMS = 5;//Golems: Breaks and places rocks.
	public static int time = 0;
	private final int TICK_FREQUENCY = 1;

	private static Tile[][] tiles;
	
	/****************************** CORE METHODS ******************************/

	public World()
	{ 
		tiles = new Tile[getTilesHorizontal()][getTilesVertical()];
		generateWorld();
	}
	
	public void update()
	{
		time++;
		if(time % TICK_FREQUENCY == 0)
		{
			for(int i = 0; i < getTilesHorizontal(); i++)
			{
				for(int j = 0; j < getTilesVertical(); j++)
				{
					tiles[i][j].update();
				}
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

	
	public static int getTilesHorizontal()
	{
		return Main.getScreenWidth() / TILE_SIZE;
	}
	
	public static int getTilesVertical()
	{
		return Main.getScreenHeight() / TILE_SIZE;
	}

	public static Tile getTile(int x, int y)
	{
		if((x < 0 || y < 0)||(x >= World.getTilesHorizontal() || y >= World.getTilesVertical()))
		{
			return null;
		}
		return tiles[x][y];
	}
	public static int getTime()
	{
		return time;
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
		float SCALE = .04f;
		float noise = (float) PerlinNoise.noise(x*SCALE, y*SCALE);
		if(noise < 0.4f)
		{
			tiles[x][y].setTerrain(new Water(noise));

		}
		else if(noise < 0.45f)
		{
			tiles[x][y].setTerrain((new Sand()));
		}
		else if(noise < 0.6f)
		{
			tiles[x][y].setTerrain(new Grass(noise));
		}
		else
		{
			if(noise < 0.7f)
			{
				tiles[x][y].setTerrain(new Mountain(noise));
			}
			else
			{
				tiles[x][y].setTerrain(new Snow(noise*2));
			}
		}

		noise = (float) PerlinNoise.noise(x * SCALE/2, y * SCALE/2);
		if(noise < 0.4)
		{
			if(tiles[x][y].terrain.getTerrain().equalsIgnoreCase("Water"))
			{
				tiles[x][y].setTerrain(new Ice());
			}
			else
			{
				tiles[x][y].setTerrain(new Snow(tiles[x][y].terrain.getElevation()));
				}
		}
		noise = (float) PerlinNoise.noise(x * (SCALE*3.5f), y * (SCALE*3.5f));
		if(noise > 0.73f)
		{
				tiles[x][y].setTerrain(new Cavern());
		}
	}

	public void generateEntities()
	{
		for(int i = 0; i < NUM_SHEEP; i++)
		{
			addEntityRandomly(new Sheep());
		}
		for(int i = 0; i < NUM_WOLVES; i++)
		{
			addEntityRandomly(new Wolf());
		}
		for(int i = 0; i < NUM_SWOLVES; i++)
		{
			addEntityRandomly(new SmartWolf());
		}
	}
		
	public void addEntityRandomly(Entity e)
	{
		// Select a random tile in the grid
		int rX = (int) (Math.random() * getTilesHorizontal());
		int rY = (int) (Math.random() * getTilesVertical());
		
		// If it is occupied, try again
		if(tiles[rX][rY].hasEntity() || !e.isValidTerrain(tiles[rX][rY].getTerrain()))
		{
			addEntityRandomly(e);
		}
		
		// If it is not occupied, assign it to a tile
		else
		{
			tiles[rX][rY].setEntity(e);
		}
			
	}
	public int getSheepAmt() {
		int temp = 0;
		for (int i = 0; i < getTilesHorizontal(); i++)
		{
			for (int j = 0; j < getTilesVertical(); j++)
			{
				if (tiles[i][j].getEntity() != null && tiles[i][j].getEntity() instanceof Sheep)
				{
					temp++;
				}
			}
		}
		return temp;
	}
	public int getWolvesAmt()
	{
		int temp = 0;
		for (int i = 0; i < getTilesHorizontal(); i++)
		{
			for (int j = 0; j < getTilesVertical(); j++)
			{
				if (tiles[i][j].getEntity() != null && tiles[i][j].getEntity() instanceof Wolf)
				{
					temp++;
				}
			}
		}
		return temp;
	} public int getSWolvesAmt()
	{
		int temp = 0;
		for (int i = 0; i < getTilesHorizontal(); i++)
		{
			for (int j = 0; j < getTilesVertical(); j++)
			{
				if (tiles[i][j].getEntity() != null && tiles[i][j].getEntity() instanceof SmartWolf)
				{
					temp++;
				}
			}
		}
		return temp;
	}
}
