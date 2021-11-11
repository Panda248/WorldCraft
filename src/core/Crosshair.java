package core;

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;

public class Crosshair {
    private float x;
    private float y;
    private boolean shooting = false;
    private Image sprite = new Image("assets/crosshair.png");
    private Input input = new Input();

    public Image getSprite() {
        return sprite;
    }

    public void followCursor()
    {
        this.x = input.getMouseX();
        this.y = input.getMouseY();
    }
}
