package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy {
    private float x;
    private float y;
    private float width;
    private float height;
    private boolean Alive = true;
    private Image sprite = new Image("assets/imgs/enemyAlive.png");

    void checkDead() throws SlickException
    {
        if(!Alive)
        {
            sprite = new Image("assets/imgs/enemyDead.png");
        }
    }
    void kill()
    {
        this.Alive = false;
    }

    public Enemy(float x, float y, float height, float width) throws SlickException {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

}
