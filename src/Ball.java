import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
/**
 * Created by Thea on 10.03.2016.
 */
public class Ball extends Circle {
    public final static int INTERVAL = 10;
    public final static double RADIUS_OF_BALL = 10;
    public final static int MAX_DX_MOVEMENT = 2;
    public final static int MAX_DY_MOVEMENT = 2;
    private double dx = MAX_DX_MOVEMENT, dy = MAX_DY_MOVEMENT;
    private PlayArea playArea;
    private boolean ballIsDead = false;
    private Long seconds = new Long(0);
    private Long seconds2 = new Long(0);
    private Long seconds3 = new Long (0);
    private long isItASecond = 0;
    private long noOfFrames = 0;
    Racket racket;
    BlockOfBricks blockOfBricks;
    BlockOfBricks lvl2;
    BlockOfBricks lvl3;
    private Timeline animation;
    private int tmp = 0;
    private int lvlTimer = 1;

    public void lvl1Timer(){
        lvlTimer = 1;
    }
    public void lvl2Timer(){
        lvlTimer = 2;
    }
    public void  lvl3Timer(){
        lvlTimer = 3;
    }
    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public Ball(PlayArea pa) {
        super(RADIUS_OF_BALL);
        this.playArea = pa;
        racket = playArea.racket;
        blockOfBricks = playArea.blockOfBricks;
        lvl2 = playArea.lvl2;
        lvl3 = playArea.lvl3;
        blockOfBricks.setColor1();
        lvl2.setColor2();
        lvl3.setColor3();
        lvl2.isNotVisible();
        lvl3.isNotVisible();
        setFill(Color.GREEN);
        startBall();

        animation = new Timeline(new KeyFrame(Duration.millis(INTERVAL),e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }
    public void timeAnimation(){
        animation = new Timeline(new KeyFrame(Duration.millis(INTERVAL),e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    public void lvl1Continues(){
        timeAnimation();
        blockOfBricks.visible();
        lvl3.isNotVisible();
        lvl2.isNotVisible();
        blockOfBricks.canCollide();
        lvl3.canNotCollide();
        lvl2.canNotCollide();
        startBall();

        setFill(Color.GREEN);

    }
    public void lvl2Starter(){
        timeAnimation();
        blockOfBricks.isNotVisible();
        lvl3.isNotVisible();
        lvl2.visible();
        blockOfBricks.canNotCollide();
        lvl3.canNotCollide();
        lvl2.canCollide();
        startBall();
        setFill(Color.DARKRED);

    }

    public void lvl3Starter(){
        timeAnimation();
        blockOfBricks.isNotVisible();
        lvl2.isNotVisible();
        lvl3.visible();
        blockOfBricks.canNotCollide();
        lvl2.canNotCollide();
        lvl3.canCollide();
        startBall();
        setFill(Color.CYAN);
    }

    public void startBall(){
        setCenterX(racket.getX() + racket.getWidth() / 2);
        setCenterY(racket.getY() - racket.getHeight());
        notDead();
    }

    public void play() {
        animation.play();
        animation.setRate(1.0);
    }

    public void pause() {
        animation.pause();
    }

    public void lvl1Speed(){animation.setRate(1.0);}

    public void lvl2Speed() {animation.setRate(1.4);}

    public void lvl3Speed() {animation.setRate(1.9);}

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1
                : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    public void notDead(){
        ballIsDead = false;
    }

    protected void moveBall() {
        switch(lvlTimer) {
            case 1:
                noOfFrames++;
                isItASecond = noOfFrames * INTERVAL;
                if (isItASecond > 1000) {
                    seconds++;
                    playArea.mainBoard.buttonBar.txtSeconds.setText(seconds.toString());
                    noOfFrames = 0;
                    playArea.mainBoard.buttonBar.txtKillCount.setText(blockOfBricks.getKillCount());
                    if(blockOfBricks.getKillCountInt() >= 80) {
                        playArea.ball.lvl2Timer();
                        playArea.ball.lvl2Speed();
                        playArea.ball.play();
                        playArea.ball.lvl2Starter();
                    }
                }

                if (!ballIsDead) {
                    bounceAgainstWall();
                    bounceAgainstRacket();
                    bounceAgainstBlockOfBricks();
                }
                break;
            case 2:
                noOfFrames++;
                isItASecond = noOfFrames * INTERVAL;
                if (isItASecond > 1000) {
                    seconds2++;
                    playArea.mainBoard.buttonBar.txtSeconds.setText(seconds2.toString());
                    noOfFrames = 0;
                    playArea.mainBoard.buttonBar.txtKillCount.setText(blockOfBricks.getKillCount());
                    if(lvl2.getKillCountInt() >= 80){
                        playArea.ball.lvl3Timer();
                        playArea.ball.lvl3Speed();
                        playArea.ball.play();
                        playArea.ball.lvl3Starter();

                    }

                }
                if (!ballIsDead) {
                    bounceAgainstWall();
                    bounceAgainstRacket();
                    bounceAgainstBlockOfBricks();
                }
                break;
            case 3:
                noOfFrames++;
                isItASecond = noOfFrames * INTERVAL;
                if (isItASecond > 1000) {
                    seconds3++;
                    playArea.mainBoard.buttonBar.txtSeconds.setText(seconds3.toString());
                    noOfFrames = 0;
                    playArea.mainBoard.buttonBar.txtKillCount.setText(blockOfBricks.getKillCount());
                    if(lvl3.getKillCountInt() >= 80){
                        System.exit(0);

                    }

                }
                if (!ballIsDead) {
                    bounceAgainstWall();
                    bounceAgainstRacket();
                    bounceAgainstBlockOfBricks();
                }
                break;
        }
    }

    void bounceAgainstWall() {
        if (getCenterX() < RADIUS_OF_BALL || getCenterX() >= playArea.getWidth() - RADIUS_OF_BALL)
            dx *= -1;
        else if (this.getCenterY() <= RADIUS_OF_BALL)
            dy *= -1;
        else if (this.getCenterY() >= playArea.getHeight() - RADIUS_OF_BALL)
            ballIsDead = true;
        this.setCenterX(getCenterX() + dx);
        this.setCenterY(getCenterY() + dy);

    }

    public boolean collide(double bx, double by, double r){
        double x0 = racket.getX() - r;
        double y0 = racket.getY() - r;
        double x1 = racket.getX() + racket.getWidth() + r;
        double y1 = racket.getY() + racket.getHeight() + r;
        if(bx >= x0 && by >= y0 && bx <= x1 && by <= y1) {
            double[] list = new double[4];

            double tmp1 = (bx - x0);
            tmp1 = Math.pow(tmp1, 2);
            double tmp2 = (by - y0);
            tmp2 = Math.pow(tmp2, 2);
            double tmp3 = tmp1 + tmp2;
            list[0] = Math.sqrt(tmp3);

            tmp1 = (bx - x0);
            tmp1 = Math.pow(tmp1, 2);
            tmp2 = (y1 - by);
            tmp2 = Math.pow(tmp2, 2);
            tmp3 = tmp1 + tmp2;
            list[1] = Math.sqrt(tmp3);

            tmp1 = (x1 - bx);
            tmp1 = Math.pow(tmp1, 2);
            tmp2 = (by - y0);
            tmp2 = Math.pow(tmp2, 2);
            tmp3 = tmp1 + tmp2;
            list[2] = Math.sqrt(tmp3);

            tmp1 = (x1 - bx);
            tmp1 = Math.pow(tmp1, 2);
            tmp2 = (y1 - by);
            tmp2 = Math.pow(tmp2, 2);
            tmp3 = tmp1 + tmp2;
            list[3] = Math.sqrt(tmp3);

            double[] list1 = new double[4];
            list1[0] = list[0] + list[1];
            list1[1] = list[3] + list[2];
            list1[2] = list[0] + list[2];
            list1[3] = list[3] + list[1];

            double tmp0 = list1[0];
            int tmp5 = 0;
            for(int i = 1; i < 4; i++){
                if(list1[i] < tmp0){
                    tmp5 = i;
                    tmp0 = list1[i];
                }
            }
            if(tmp5 < 2){
                tmp = 1;
            }else{

                tmp = 2;
            }

            return true;
        }else return false;
    }

   void bounceAgainstRacket() {
       collide(getCenterX(), getCenterY(), RADIUS_OF_BALL);
       if(tmp == 1){
           dy *= -1;
       }else if(tmp == 2){
           dx *= -1;
       }tmp = 0;
    }


    void bounceAgainstBlockOfBricks(){

        blockOfBricks.touchBlockOfBricks(getCenterX(), getCenterY(), RADIUS_OF_BALL);
        if(blockOfBricks.collisionValue == 1){
            dy *= -1;
        }else if(blockOfBricks.collisionValue == 2){
            dx *= -1;
        }
        lvl2.touchBlockOfBricks(getCenterX(), getCenterY(), RADIUS_OF_BALL);
        if(lvl2.collisionValue == 1){
            dy *= -1;
        }else if(lvl2.collisionValue == 2){
            dx *= -1;
        }
        lvl3.touchBlockOfBricks(getCenterX(), getCenterY(), RADIUS_OF_BALL);
        if(lvl3.collisionValue == 1){
            dy *= -1;
        }else if(lvl3.collisionValue == 2){
            dx *= -1;
        }
    }
}
