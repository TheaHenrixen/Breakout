import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Created by Thea on 10.03.2016.
 */
public class BlockOfBricks extends Group{
    private final int SPACING = 2;
    public static int collisionValue = 0;
    private PlayArea playArea;
    private int numWidth, numHeight;
    private int brickWidth, brickHeight;
    private int leftX, rightX, upperY, lowerY;
    ArrayList<Brick> deletedBricks = new ArrayList<>();
    private int killCount = 0;
    private int colorCount = 0;
    private double red, blue, green = 0;
    boolean collidable = false;
    private double colorMod = 0.25;

    public String getKillCount(){
        return Integer.toString(killCount);
    }

    public int getKillCountInt() {return killCount;}

    public int getLeftX() {
        return leftX;
    }

    public int getRightX() {
        return rightX;
    }

    public int getUpperY() {
        return upperY;
    }

    public int getLowerY() {
        return lowerY;
    }

    public void isNotVisible(){
        setVisible(false);
    }
    public void visible(){
        setVisible(true);
    }
    public void canCollide(){
        collidable = true;
    }
    public void canNotCollide(){
        collidable = false;
    }
    public void setColor1(){
        colorMod = 0.25;
    }
    public void setColor2(){
        colorMod = 0.75;
    }
    public void setColor3(){
        colorMod = 1;
    }

    public BlockOfBricks(PlayArea pa, int numHeight, int numWidth, double colorMod2) {
        Random r = new Random();

        int totalWidth = (int) pa.getPrefWidth();
        int totalHeight = (int) pa.getPrefHeight() / 2;
        colorMod = colorMod2;

        brickWidth = (totalWidth - (SPACING * numWidth - 1))/ (numWidth + 2);
        brickHeight = totalHeight / (numHeight + 2);

        int startX = brickWidth;
        int nowX = leftX = startX;
        int nowY = upperY = brickWidth;
        for (int i = 1; i <= numHeight * numWidth; i++) {
            if(colorCount < 30){
                red = 0.0 * colorMod;
                blue = 0.8 * colorMod;
                green = 0.0 * colorMod;
            }else if (colorCount < 60 ){
                red = 0.0 * colorMod;
                blue = 0.0 * colorMod;
                green = 1 * colorMod;
            }else if (colorCount < 80) {
                red = 1 * colorMod;
                blue = 0 * colorMod;
                green = 0 * colorMod;
            }else {
                red = 0.5 * colorMod;
                blue = 0.1 * colorMod;
                green = 0.8 * colorMod;
            }
            colorCount++;
            Brick thisBrick = new Brick(nowX, nowY, brickWidth, brickHeight, Color.color(red, blue, green, 1));
            getChildren().add(thisBrick);
            if (i % numWidth == 0) {
                nowX = startX;
                nowY += brickHeight + SPACING;
            }
            else
                nowX += brickWidth + SPACING;
        }

        for (int i = 0; i < (numWidth * numHeight) * 0.2; i++)
            getChildren().remove(r.nextInt(getChildren().size()));

        pa.getChildren().addAll(this);

        lowerY = upperY + (brickHeight + SPACING) * numHeight;
        rightX = startX + (brickWidth + SPACING) * numWidth;

    }

    public void touchBlockOfBricks(double x, double y, double r){

        if(collidable){
            collisionValue = 0;

            for (Node n : getChildren()) {
                Brick brick = (Brick) n;

                if(brick.collide(x, y, r)){
                    collisionValue = brick.tmp;
                    deletedBricks.add(brick);
                    killCount++;
                }
            }
            for (Brick brick2 : deletedBricks) {
                getChildren().remove(brick2);
            }
        }


    }
}
