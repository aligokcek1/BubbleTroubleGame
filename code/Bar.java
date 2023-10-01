/* Ali Gökçek
2021400012
16.04.2023
Bar class includes draw, update and reset functions that is used in environment class later.
 */
public class Bar {
    private double x;
    private final double y;
    private int rgb2;       // BECAUSE ONLY RGB2 VALUE CHANGES
    private final double height;
    private double width;

    public Bar(){
        this.x = Ali_Gokcek.maxX/2;
        this.y = Ali_Gokcek.minY/2;
        this.rgb2 = 255;
        this.height = -Ali_Gokcek.minY/2;
        this.width = Ali_Gokcek.maxX;
    }
    public void draw(){
        StdDraw.setPenColor(255,rgb2,0);
        StdDraw.filledRectangle(x, y, width/2,height/2);
    }

    public void update(double timePassed){
        int totalTime = Environment.TOTAL_GAME_DURATION;
        double ratio = (totalTime-timePassed)/totalTime;
        width = Ali_Gokcek.maxX * ratio;
        x = Ali_Gokcek.maxX/2 * ratio;
        rgb2 = (int) (255 * ratio);
    }

    public void reset(){
        x = Ali_Gokcek.maxX/2;
        rgb2 = 255;
        width = Ali_Gokcek.maxX;
    }
}
