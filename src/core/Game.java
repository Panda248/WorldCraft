// added a comment

package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState
{	
	int id;
	Graphics draw = new Graphics();
	Crosshair crosshair;
	Game(int id) 
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		gc.setShowFPS(true);
		crosshair = new Crosshair();
		gc.setMouseCursor("assets/imgs/mouse.png", 0, 0);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		draw.drawImage(crosshair.getSprite(), crosshair.getX(), crosshair.getY());

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		crosshair.followCursor();

	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		
	}


	public void keyPressed(int key, char c)
	{
		
	}
	
	public void mousePressed(int button, int x, int y)
	{
		
	}
	
	public int getID() 
	{
		return id;
	}


}
