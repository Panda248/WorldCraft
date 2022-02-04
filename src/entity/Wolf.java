package entity;

import org.newdawn.slick.Graphics;
import terrain.Terrain;

public class Wolf extends Entity
{
    @Override
    public boolean isValidTerrain(Terrain t) {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
