package core;

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Crosshair {
    private float x;
    private float y;
    private boolean shooting = false;
    private Image sprite = new Image("assets/crosshair.png");
    private Input input = new Input(1080);

    public Crosshair() throws SlickException {
    }

    public Image getSprite() {
        return sprite;
    }

    public void followCursor()
    {
        this.x = input.getMouseX();
        this.y = input.getMouseY();
    }
}
