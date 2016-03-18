import com.sun.scenario.effect.impl.sw.java.JSWBlend_GREENPeer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by Thea on 10.03.2016.
 */
public class MainBoard extends Application {
    PlayArea playArea;
    public ButtonBar buttonBar;
    BorderPane board;

    @Override
    public void start(Stage primaryStage) {
        playArea = new PlayArea(this);
        buttonBar = new ButtonBar();
        board = new BorderPane();
        board.setCenter(playArea);
        board.setBottom(buttonBar);

        Scene scene = new Scene(board);
        primaryStage.setTitle("Breakout game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        buttonBar.btStart.setOnAction(e -> {
            playArea.ball.lvl1Timer();
            playArea.ball.play();
            playArea.ball.blockOfBricks.canCollide();
            playArea.ball.blockOfBricks.visible();
            buttonBar.btStart.setVisible(false);
            buttonBar.btLevel1.setDisable(true);

        });

        buttonBar.btPause.setOnAction(e -> {
            playArea.ball.pause();

        });

        buttonBar.btLevel1.setOnAction(e -> {
            playArea.ball.lvl1Timer();
            playArea.ball.lvl1Continues();
            playArea.ball.play();
            playArea.ball.lvl1Speed();
        });

        buttonBar.btLevel2.setOnAction(e -> {
            playArea.ball.lvl2Timer();
            playArea.ball.lvl2Speed();
            playArea.ball.play();
            playArea.ball.lvl2Starter();

        });

        buttonBar.btLevel3.setOnAction(e -> {
            playArea.ball.lvl3Timer();
            playArea.ball.lvl3Speed();
            playArea.ball.play();
            playArea.ball.lvl3Starter();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
