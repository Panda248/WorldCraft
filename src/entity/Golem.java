package entity;

import core.Tile;
import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import terrain.*;

public class    Golem extends Entity
{
    private int numrocks = 5;
    @Override
    public boolean isValidTerrain(Terrain t) {
        return t instanceof Grass || t instanceof Stone || t instanceof Mountain || t instanceof Snow;
    }

    @Override
    public void update() {
        move(tile.getX(), tile.getY());
        if(Math.random() > 0.5)
        {
            smash();
        }
        if(Math.random() > 0.5 && numrocks > 0)
        {
            place();
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(new Color(numrocks*10, numrocks*10, numrocks*30));
        g.fillOval(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
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
        numrocks++;
        float temp = this.tile.getTerrain().getElevation()-0.1f;
        this.tile.setTerrain(new Stone(temp));
    }
    public void place()
    {
        numrocks--;
        float temp = this.tile.getTerrain().getElevation()+0.1f;
        this.tile.setTerrain(new Stone(temp));
    }

}
