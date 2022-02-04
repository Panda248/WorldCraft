package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.sql.Array;
import java.util.ArrayList;

public class Game extends BasicGameState 
{	
	private int id;
	private ArrayList<GraphPoint> graphPoints = new ArrayList<>();
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
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		world.update();
		if (graphPoints.size() >= 500) graphPoints.remove(0);
		graphPoints.add(new GraphPoint(Main.getScreenWidth(), 300 - (int) (world.getSheepAmt()/6.5)));
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		world.render(g);
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(Main.getScreenWidth() - 500, 0, 500, 300);

		graphPoints.forEach((GraphPoint p) -> {
			p.x--;
			System.out.println(p.y);
			g.setColor(new Color(2 * (300 - p.y), (int) (2 * p.y), 0, 255));
			g.drawRect(p.x, p.y, 1, 300 - p.y);
		});
		g.setColor(Color.white);
		g.drawString(world.getSheepAmt() + "",Main.getScreenWidth() - 50, 0);
	}

}
