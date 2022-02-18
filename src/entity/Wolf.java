package entity;

import core.Tile;
import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import terrain.*;

import java.util.ArrayList;

public class Wolf extends Entity
{
    protected int lastUpdateTime = 0;
    private Entity targetSheep;
    private int food = 10;
    private final int MAX_FOOD = 10;
    private final int REPRODUCTION_REQ = 3;
    @Override
    public boolean isValidTerrain(Terrain t) {
        return (t instanceof Grass || t instanceof Sand) ||(t instanceof Snow);
    }

    @Override
    public void update() {
        //hunger();
        hunt(tile.getX(), tile.getY());
        reproduce();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(50, 50, 50));
        g.fillOval(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
    }
    public void move()
    {
        Tile destination = this.tile;
        int x = tile.getX();
        int y = tile.getY();
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
            eat(destination);
            tile.clearEntity();
            destination.setEntity(this);
        }
    }
    public void hunger()
    {
        if(food > 0)
        {
            food--;
        }
        else
        {
            tile.clearEntity();
        }
    }
    public void eat(Tile tile)
    {
        if(tile.getEntity() instanceof Sheep)
        {
            this.food += ((Sheep) tile.getEntity()).getFood()/10;
            tile.clearEntity();
        }
    }
    public void reproduce() {
        int x = tile.getX();
        int y = tile.getY();

        if (food > REPRODUCTION_REQ) {
            if (x > 0) {
                Tile babyTile = World.getTile(x - 1, y);
                babyTile.setEntity(new Sheep());
                food -= 5;
            }
        }
    }
    private void hunt(int x, int y)
    {
        if(seeSheep(x, y))
        {
            chaseSheep(x ,y);
        }
        else
        {
            move();
        }

    }

    private boolean seeSheep(int x, int y)
    {
        boolean result = false;
        float targetJ = 10;
        float targetI = 10;
        for(int i = -4; i < 5; i++)
        {
            for(int j = -4; j < 5; j++)
            {
                if((x + j >= 0  && x + j < World.getTilesHorizontal() - 1) && (y + i >= 0 && y + i < World.getTilesVertical() - 1)) {
                    if (World.getTile(x + j, y + i).getEntity() instanceof Sheep)
                    {
                        if (Math.abs(j) < targetJ && Math.abs(i) < targetI)
                        {
                            result = true;
                            targetI = i;
                            targetJ = j;
                            this.targetSheep = World.getTile(x + j, y + i).getEntity();
                        }
                    }
                }
            }
        }
        return result;
    }
    private void chaseSheep(int x, int y)
    {

    }
}
