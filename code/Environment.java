/* Ali Gökçek
2021400012
16.04.2023
Environment class includes some constants, draw function that draws every element, update function
that change positions, some function to check collisions, game screen drawer and game resetter.
 */
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Environment {
    public static final int TOTAL_GAME_DURATION = 40000;
    public static final int PAUSE_DURATION = 15;
    public static final double GRAVITY = 0.000003 * Ali_Gokcek.maxY;
    public double maxX;
    public double minX;
    public double minY;
    public double maxY;


    public Environment() {
        this.maxX = Ali_Gokcek.maxX;
        this.minX = Ali_Gokcek.minX;
        this.minY = Ali_Gokcek.minY;
        this.maxY = Ali_Gokcek.maxY;

    }

    public void draw(Bar bar, Player player, Arrow arrow, ArrayList<Ball> balls) {
        StdDraw.clear();
        StdDraw.picture(maxX / 2, maxY / 2, "src/images/background.png", maxX, maxY);
        StdDraw.picture(maxX / 2, minY / 2, "src/images/bar.png", maxX, -minY);
        bar.draw();
        arrow.draw();
        player.draw();
        for (Ball ball : balls) {
            ball.draw();
        }
    }

    public void update(double timePassed, Bar bar, Player player, Arrow arrow, ArrayList<Ball> balls, ArrayList<Ball> cloneballs) {
        cloneballs = (ArrayList<Ball>) balls.clone();
        if (cloneballs.isEmpty()){         // IF EVERY BALL HAS POPPED
            if(!gameScreenDrawer("w")){
                System.exit(1);
            }
            else{
                resetGame(player, arrow, bar, balls);
                return;
            }
        }
        bar.update(timePassed);
        player.move();
        arrow.move();

        // CHECK FOR COLLISIONS
        for (Ball ball : cloneballs) {
            if (collisionWithPlayer(player, ball)) {
                if (!gameScreenDrawer("o")){
                    System.exit(1);
                }
                else{
                    resetGame(player, arrow, bar, balls);
                    break;
                }
            }
            if (collisionWithArrow(arrow, ball)) {
                arrow.setWillMove(false);       // IF ARROW HITS A BALL, SET THE ARROW'S POSITION
                int ballLevel = ball.getLevel();
                if (ballLevel > 0) {        // SPLITTING THE BIGGER BALL, AND CREATING SMALLER ONES
                    balls.remove(ball);
                    balls.add(new Ball(ballLevel - 1, ball.getX(), ball.getY(), ball.getVx(), 0.1, timePassed));
                    balls.add(new Ball(ballLevel - 1, ball.getX(), ball.getY(), -ball.getVx(), 0.1, timePassed));
                } else {
                    balls.remove(ball);
                }
                break;
            }
            ball.move(timePassed);
        }
    }

    // THE FUNCTION TO CHECK WHETHER ARROW HITS ANY BALLS
    public static boolean collisionWithArrow(Arrow arrow, Ball ball) {
        double ballRightX = ball.getX() + ball.getRadius(), ballLeftX = ball.getX() - ball.getRadius();
        double ballBotX = ball.getX();
        double ballBotY = ball.getY() - ball.getRadius();
        double arrowLeftX = arrow.getX() - arrow.getWidth() / 2, arrowRightX = arrow.getX() + arrow.getWidth() / 2;
        double arrowTopY = arrow.getY() + arrow.getHeight() / 2;
        if ((ballRightX >= arrowLeftX && ballBotY <= arrowTopY && ballLeftX <= arrowLeftX) || (ballLeftX <= arrowRightX && ballBotY <= arrowTopY && ballRightX >= arrowRightX)) {
            return true;
        }
        if ((ballBotX <= arrowRightX && ballBotX >= arrowLeftX) && (ballBotY <= arrowTopY)) {
            return true;
        }
        return false;
    }

    // THE FUNCTION TO CHECK WHETHER ANY BALL HITS THE PLAYER
    public static boolean collisionWithPlayer(Player player, Ball ball) {
        double ballRightX = ball.getX() + ball.getRadius(), ballLeftX = ball.getX() - ball.getRadius();
        double ballBotX = ball.getX();
        double ballBotY = ball.getY() - ball.getRadius();
        double playerTopY = player.getY() + player.getHeight() / 2;
        double playerLeftX = player.getX() - player.getWidth() / 2, playerRightX = player.getX() + player.getWidth() / 2;
        if ((ballRightX >= playerLeftX && ballBotY <= playerTopY && ballLeftX <= playerLeftX) || (ballLeftX <= playerRightX && ballBotY <= playerTopY && ballRightX >= playerRightX)) {
            return true;}
        if ((ballBotX <= playerRightX && ballBotX >= playerLeftX) && (ballBotY <= playerTopY)) {
            return true;

        }
        return false;
    }
    public static int a = 0;

    // END SCREEN
    public static boolean gameScreenDrawer(String wORo) {
        while (a == 0) {                // LOOP UNTIL USER PRESSES "Y" OR "N"
            if (wORo.equals("w")) {
                StdDraw.setPenColor(Color.black);
                StdDraw.picture(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2.18, "src/images/game_screen.png", Ali_Gokcek.maxX / 3.8, Ali_Gokcek.maxY / 4);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                StdDraw.text(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2, "YOU WON!");
                StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
                StdDraw.text(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2.3, "To Replay Click \"Y\"");
                StdDraw.text(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2.6, "To Quit Click \"N\"");
                StdDraw.show();
            } else {
                StdDraw.setPenColor(Color.black);
                StdDraw.picture(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2.18, "src/images/game_screen.png", Ali_Gokcek.maxX / 3.8, Ali_Gokcek.maxY / 4);
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                StdDraw.text(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2, "GAME OVER!");
                StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
                StdDraw.text(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2.3, "To Replay Click \"Y\"");
                StdDraw.text(Ali_Gokcek.maxX / 2, Ali_Gokcek.maxY / 2.6, "To Quit Click \"N\"");
                StdDraw.show();
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_Y)){
                a += 1;
                return true;
            }
            else if (StdDraw.isKeyPressed(KeyEvent.VK_N)){
                a += 1;
                return false;
            }
        }
        return true;
    }

    // IF USER PRESSES "Y" RESETS GAME
    public static void resetGame(Player player, Arrow arrow, Bar bar, ArrayList<Ball> balls){
        balls.clear();
        for (int i = 0; i < 3; i++) {
            if (i == 1){
                Ball ball = new Ball(i, Ali_Gokcek.maxX / (i+2), Ali_Gokcek.maxY / 2, -Ali_Gokcek.ballVx, 0.0, 0.0);
                balls.add(ball);
            }
            else{
                Ball ball = new Ball(i, Ali_Gokcek.maxX / (i+2), Ali_Gokcek.maxY / 2, Ali_Gokcek.ballVx, 0.0, 0.0);
                balls.add(ball);
            }
        }
        player.reset();
        arrow.reset();
        bar.reset();
        Ali_Gokcek.startTime = System.currentTimeMillis();
        a = 0;
    }
}

