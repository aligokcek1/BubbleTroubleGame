/* Ali Gökçek
2021400012
16.04.2023
Similar to other classes, some getter, move and update functions.
 */
public class Ball {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private int level;
    private double radius;
    private double maxHeight;
    private double flyTime;
    public double getVx() {
        return vx;
    }
    public int getLevel() {
        return level;
    }
    public Ball(int level, double x, double y, double vx, double vy, double flyTime){
        this.x = x;
        this.y = y;
        this.level = level;
        this.vx = vx;
        this.vy = vy;
        this.radius = Ali_Gokcek.maxY * 0.0175 * Math.pow(2, level);
        this.maxHeight = 1.4 * Math.pow(1.75, level);
        this.flyTime = flyTime;
    }
    public double getRadius() {
        return radius;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void draw(){
        StdDraw.picture(x, y, "src/images/ball.png", radius*2, radius*2);
    }
    public void move(double time){
        double t = time - flyTime;
        vy -= Environment.GRAVITY * t;      // BASICALLY Vf = Vi - gravity * time
        x += vx;
        y += vy;
        if (x + radius >= 16.0 || x - radius <= 0.0){
            vx = -vx;
        }
        if (y - radius <= 0.0){
            y = radius;
            vy = Math.sqrt(2.0*maxHeight*Environment.GRAVITY*350);
            flyTime = time;
        }
    }
}
