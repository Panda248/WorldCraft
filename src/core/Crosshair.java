package core;

import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Crosshair{
    private float x;
    private float y;
    private float height = 50f;
    private float width = 50f;
    private boolean shooting = false;
    private Image sprite = new Image("assets/imgs/crosshair.png");
    private Input input = new Input(1080);

        public Crosshair() throws SlickException {
        }


        public Image getSprite() {
        return sprite;
    }
        public float getHeight() {
            return height;
        }
        public float getWidth() {
            return width;
        }

        public float getX() {
                return x;
                }

        public float getY() {
            return y;
        }
    public void followCursor()
    {
        this.x = input.getMouseX()- width/2.05f;
        this.y = input.getMouseY() - height/1.95f;
    }

}
