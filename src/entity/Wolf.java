package entity;

import core.Tile;
import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import terrain.*;

import java.util.ArrayList;
import java.util.Objects;

public class Wolf extends Entity
{
    protected int lastUpdateTime = 0;
    private float food = 50;
    private final int MAX_FOOD = 100;
    private final int REPRODUCTION_REQ = 50;
    @Override
    public boolean isValidTerrain(Terrain t) {
        return (t instanceof Grass || t instanceof Sand) ||(t instanceof Snow || t instanceof Ice);
    }

    @Override
    public void update() {
        if(lastUpdateTime != World.getTime())
        {
            eat(this.tile);
            hunger();
            reproduce();
            move();
        }
        lastUpdateTime = World.getTime();
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
            food-=0.25;
        }
        else
        {
            tile.clearEntity();
        }
    }
    public void eat(Tile tile)
    {
        for(int i = -2; i < 3; i ++)
        {
            for(int j = -2; j < 3; j++)
            {
                if((World.getTile(tile.getX() + i, tile.getY() + j)) != null)
                {
                    if ((World.getTile(tile.getX() + i, tile.getY() + j)).getEntity() instanceof Sheep) {
                        this.food += ((Sheep) World.getTile(tile.getX() + i, tile.getY() + j).getEntity()).getFood() / 2;
                        World.getTile(tile.getX() + i, tile.getY() + j).clearEntity();
                    }
                }
            }
        }
    }
    public void reproduce() {
        int x = tile.getX();
        int y = tile.getY();

        if (food > REPRODUCTION_REQ) {
            if (x > 0) {
                Tile babyTile = World.getTile(x - 1, y);
                babyTile.setEntity(new Sheep());
                food -= this.REPRODUCTION_REQ/2;
            }
        }
    }

}
