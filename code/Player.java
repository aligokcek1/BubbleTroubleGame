/* Ali Gökçek
2021400012
16.04.2023
Same as bar class. Moreover, some "get" functions that is used in Arrow class and collision checkers.
 */
import java.awt.event.KeyEvent;
public class Player {
    private double x;
    private final double y;
    private final double width;
    private final double height;
    private final double vx;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Player(){
        this.x = Ali_Gokcek.maxX/2;
        this.y = 0.5;
        this.height = 1.0;
        this.width = height*27/37;
        this.vx = 130.0/1000;
    }

    public void draw(){
        StdDraw.picture(x,y, "src/images/player_back.png",width,height);
    }
    public void move() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
            x -= vx;
            if (x - width/2 < Ali_Gokcek.minX){
                x = width/2;
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
            x += vx;
            if (x + width/2 > Ali_Gokcek.maxX){
                x = Ali_Gokcek.maxX - width/2;
            }
        }
    }
    public void reset(){
        x = Ali_Gokcek.maxX/2;
    }
}
