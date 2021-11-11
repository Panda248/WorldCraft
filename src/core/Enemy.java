package core;

import org.newdawn.slick.Image;

public class Enemy {
    private float x;
    private float y;
    private float width;
    private float height;
    private boolean Alive = true;
    private Image sprite = new Image("assets/imgs/enemyAlive.png");

    void checkDead()
    {
        if(!Alive)
        {
            sprite = new Image("assets/imgs/enemyDead.png");
        }
        else
        {
            if()
        }
    }
    public Enemy(float x, float y, float height, float width)
    {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

}
