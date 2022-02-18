package entity;

import core.Tile;
import core.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import terrain.Grass;
import terrain.Sand;
import terrain.Snow;
import terrain.Terrain;

public class SmartWolf extends Wolf
{
    private Entity targetSheep;
    private float food = 50;
    private final int MAX_FOOD = 100;
    private final int REPRODUCTION_REQ = 50;
    @Override
    public boolean isValidTerrain(Terrain t) {
        return super.isValidTerrain(t);
    }

    @Override
    public void update() {
        if(lastUpdateTime != World.getTime())
        {
            hunger();
            hunt(this.tile.getX(), this.tile.getY());
            reproduce();
        }
        lastUpdateTime = World.getTime();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        {
            g.drawOval(tile.getX() * World.TILE_SIZE * 1.5f, tile.getY() * World.TILE_SIZE * 1.5f, World.TILE_SIZE, World.TILE_SIZE);
        }
        g.setColor(new Color(100, 100, 100));
        g.fillOval(tile.getX() * World.TILE_SIZE, tile.getY() * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE);
    }
    public void hunger()
    {
        super.hunger();
    }
    public void eat(Tile tile)
    {
        super.eat(tile);
        targetSheep = null;
    }
    public void reproduce() {
        super.reproduce();
    }
    private void hunt(int x, int y)
    {
        if(targetSheep != null)
        {
            chaseSheep(x, y);
        }
        else if(seeSheep(x, y))
        {
            chaseSheep(x ,y);
        }
        else
        {
            move();
        }
    }
    public void move()
    {
        super.move();
    }
    private boolean seeSheep(int x, int y)
    {
        boolean result = false;
        float targetJ = 10;
        float targetI = 10;
        for(int i = -49; i < 50; i++)
        {
            for(int j = -49; j < 50; j++)
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
    private void chaseSheep(int x, int y) //very basic and stupid not even pathfinding.
    {
        Tile destination = this.tile;
        float slope = (this.targetSheep.tile.getY()- this.tile.getY())/(this.targetSheep.tile.getX()-this.tile.getX() + 0f);
        if(slope >= 0)
        {
            if(slope > 1 && y > 0)//north
            {
                destination = World.getTile(x, y - 1);
            }
            else if(x < World.getTilesHorizontal() - 1)//east
            {
                destination = World.getTile(x + 1, y);
            }
        }
        else if(slope < 0)
        {
            if(slope < -1 && y < World.getTilesVertical() - 1)//south
            {
                destination = World.getTile(x, y+ 1);
            }
            else if(x > 0)//west
            {
                destination = World.getTile(x - 1, y);
            }
        }
        if (canEnter(destination)) {
            eat(destination);
            tile.clearEntity();
            destination.setEntity(this);
        }
    }
}
