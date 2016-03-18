import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * Created by Thea on 10.03.2016.
 */
public class Racket extends Rectangle {
    static final int NO_OF_SECTORS = 5;
    static final int LEFTMOST = 0;
    static final int LEFT_OF_CENTER = 1;
    static final int CENTER = 2;
    static final int RIGHT_OF_CENTER = 3;
    static final int RIGHTMOST = 4;

    private PlayArea playArea;
    private Ball ball;
    public Racket(PlayArea pa) {

        super(pa.getPrefWidth()/2,(pa.getPrefHeight()/10)*8, 80, 20);
        this.playArea = pa;
        this.setFill(Color.BLACK);
    }

    public void move(MouseEvent e) {
        if (getWidth() + getX() <= playArea.getWidth())
            setX(e.getX());
        else
            setX(playArea.getWidth() - getWidth());
    }


}
