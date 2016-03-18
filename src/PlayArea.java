import javafx.scene.layout.Pane;
/**
 * Created by Thea on 10.03.2016.
 */
public class PlayArea extends Pane {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    Racket racket;
    Ball ball;
    BlockOfBricks blockOfBricks;
    MainBoard mainBoard;
    BlockOfBricks lvl2;
    BlockOfBricks lvl3;

    public PlayArea(MainBoard mb) {
        mainBoard = mb;
        this.setPrefWidth(WIDTH);
        this.setPrefHeight(HEIGHT);
        this.setStyle("-fx-border-color: black");
        blockOfBricks = new BlockOfBricks(this, 10, 10, 0.25);
        lvl2 = new BlockOfBricks(this, 10, 10, 0.75);
        lvl3 = new BlockOfBricks(this, 10, 10, 1.0);
        racket = new Racket(this);
        ball = new Ball(this);
        getChildren().addAll(racket,ball);

        setOnMouseMoved(e -> {racket.move(e);});
    }
}
