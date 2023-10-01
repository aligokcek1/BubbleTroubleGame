/* Ali Gökçek
2021400012
16.04.2023
Basically, my code draws each game object and update their position on environment class.
While each updating, checks collisions and continues differently depending on conditions.
 */

import java.util.ArrayList;
public class Ali_Gokcek {
    // Some variables that I used in other classes
    static final double maxX = 16.0, maxY = 9.0,minX = 0.0, minY = -1.0;
    static final double ballVx = 1000.0/15000;
    static double startTime;
    public static void main(String[] args) {

        // Setting canvas
        int canvasWidth = 800, canvasHeight = 500;
        StdDraw.setCanvasSize(canvasWidth,canvasHeight);
        StdDraw.setXscale(minX,maxX);
        StdDraw.setYscale(minY,maxY);
        StdDraw.enableDoubleBuffering();

        // Creating objects
        Environment environment = new Environment();
        Bar bar = new Bar();
        Player player = new Player();
        Arrow arrow = new Arrow(player);

        ArrayList<Ball> balls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i == 1){
                Ball ball = new Ball(i, maxX / (i+2), maxY / 2, -ballVx, 0.0, 0.0);
                balls.add(ball);
            }
            else{
                Ball ball = new Ball(i, maxX / (i+2), maxY / 2, ballVx, 0.0, 0.0);
                balls.add(ball);
            }
        }
        ArrayList<Ball> cloneBalls = new ArrayList<>();

        startTime = System.currentTimeMillis();
        while (true) {
            cloneBalls = (ArrayList<Ball>) balls.clone();
            double currentTime = System.currentTimeMillis();
            double timePassed = currentTime - startTime;
            // IF THE TIME IS OVER
            if (timePassed > Environment.TOTAL_GAME_DURATION){
                if(!Environment.gameScreenDrawer("o")){   //IF USER PRESSED "N"
                    System.exit(1);
                }
                else{
                    Environment.resetGame(player, arrow, bar, balls);   //USER PRESSED "Y"
                    continue;
                }
            }
            environment.draw(bar, player, arrow, balls);
            StdDraw.show();
            // UPDATING AND MOVING EVERY OBJECTS
            environment.update(timePassed, bar, player, arrow, balls, cloneBalls);
            StdDraw.pause(Environment.PAUSE_DURATION);
        }

    }

}