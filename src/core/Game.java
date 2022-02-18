package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.sql.Array;
import java.util.ArrayList;

public class Game extends BasicGameState 
{	
	private int id;
	private ArrayList<GraphPoint> sheepGraphPoints = new ArrayList<>();
	private ArrayList<GraphPoint> wolfGraphPoints = new ArrayList<>();
	private ArrayList<GraphPoint> sWolfGraphPoints = new ArrayList<>();
	World world;

	public Game(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		world = new World();
		gc.setShowFPS(Main.debug);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		world.update();
		if (sheepGraphPoints.size() >= 500) sheepGraphPoints.remove(0);
		sheepGraphPoints.add(new GraphPoint(Main.getScreenWidth(), 300 - (int) (world.getSheepAmt()/7)));
		if (wolfGraphPoints.size() >= 500) wolfGraphPoints.remove(0);
		wolfGraphPoints.add(new GraphPoint(Main.getScreenWidth(), 300 - (int) (world.getWolvesAmt()/7)));
		if (sWolfGraphPoints.size() >= 500) sWolfGraphPoints.remove(0);
		sWolfGraphPoints.add(new GraphPoint(Main.getScreenWidth(), 300 - (int) (world.getSWolvesAmt()/7)));
		sheepGraphPoints.forEach((GraphPoint p) -> {
			p.x--;
		});
		wolfGraphPoints.forEach((GraphPoint p) -> {
			p.x--;
		});
		sWolfGraphPoints.forEach((GraphPoint p) -> {
			p.x--;
		});
		if(Main.debug)
		{
			gc.setShowFPS(true);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		world.render(g);
		if(Main.debug)
		{
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(Main.getScreenWidth() - 500, 0, 500, 300);

			sheepGraphPoints.forEach((GraphPoint p) -> {
				g.setColor(new Color(2 * (300 - p.y), (int) (2 * p.y), 0, 255));
				g.drawRect(p.x, p.y, 1, 300 - p.y);
			});
			wolfGraphPoints.forEach((GraphPoint p) -> {
				g.setColor(new Color(2 * (2 * p.y), (int) (100 - p.y), 0, 255));
				g.drawRect(p.x, p.y, 1, 300 - p.y);
			});
			sWolfGraphPoints.forEach((GraphPoint p) -> {
				g.setColor(new Color(2 * (4 * p.y), (int) (200 - p.y), 0, 255));
				g.drawRect(p.x, p.y, 1, 300 - p.y);
			});
			g.setColor(Color.white);
			g.drawString("Sheep: " + world.getSheepAmt(), Main.getScreenWidth() - 100, 0);
			g.drawString("Wolves: " + (world.getWolvesAmt() - world.getSWolvesAmt()), Main.getScreenWidth() - 100, 20);
			g.drawString("Smart Wolves: " + world.getSWolvesAmt(), Main.getScreenWidth() - 150, 40);
		}
	}

	public void keyPressed(int key, char c)
	{
		if(key == Input.KEY_F3)
		{
			Main.debug = !Main.debug;
		}
	}

}
