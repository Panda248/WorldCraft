package entity;

import core.Tile;
import core.World;
import org.newdawn.slick.Graphics;
import terrain.Terrain;

public class Golem extends Entity
{
    private int numrocks = 5;
    @Override
    public boolean isValidTerrain(Terrain t) {
        return false;
    }

    @Override
    public void update() {
        move(tile.getX(), tile.getY());
        if(Math.random() > 0.5)
        {
            smash();
        }
        if(Math.random() > 0.5)
        {
            place();
        }
    }

    @Override
    public void render(Graphics g) {

    }
    public void move(int x, int y)
    {
        Tile destination = this.tile;
        double r = Math.random();

        if (r < .25 && x > 0)//West
        {
            destination = World.getTile(x - 1, y);
        }
        if((r >= 0.25 && r < 0.5) && x < World.getTilesHorizontal() - 1) //east
        {
            destination = World.getTile(x + 1, y);
        }
        if((r >=0.5 && r < 0.75) && y > 0) //north
        {
            destination = World.getTile(x, y - 1);
        }
        if(r >=0.75 && y < World.getTilesVertical() - 1)//south
        {
            destination = World.getTile(x, y+ 1);
        }
        if (canEnter(destination)) {
            this.tile.clearEntity();
            destination.setEntity(this);
        }
    }
    public void smash()
    {

    }
    public void place()
    {}

}
