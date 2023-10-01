/* Ali Gökçek
2021400012
16.04.2023
Similar to others. But this one has "willmove" variable that is used to update arrow position differently.
 */
import java.awt.event.KeyEvent;

public class Arrow {
    private double x;
    private double y;
    private double vy;
    private Player player;
    private double height;
    private double width;
    private boolean willMove;
    public Arrow(Player player){
        this.x = player.getX();
        this.y = player.getY();
        this.vy = 200.0/1500;
        this.player = player;
        this.height = player.getHeight();
        this.width = player.getWidth()/4;
        this.willMove = false;      // INITIALLY, IT SHOULD NOT MOVE
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void draw(){
        StdDraw.picture(x,y,"src/images/arrow.png",width,height);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public void setWillMove(boolean willMove) {
        this.willMove = willMove;
    }

    public void move(){
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)){
            setWillMove(true);
        }
        if (willMove){      // IF IT SHOULD MOVE
            y += vy;
            height += 2*vy;
            if (height > Ali_Gokcek.maxY){
                x = player.getX();
                y = player.getY();
                height = player.getHeight();
                setWillMove(false);
            }
        }
        else{           // ELSE STICK TO THE PLAYER'S POSITIONS.
            height = player.getHeight();
            width = player.getWidth()/4;
            x = player.getX();
            y = player.getY();
        }
    }

    public void reset(){
        willMove = false;
    }
}
