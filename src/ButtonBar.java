import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
/**
 * Created by Thea on 10.03.2016.
 */
public class ButtonBar extends HBox{
    Button btStart = new Button ("Start");
    Button btPause = new Button ("Pause");
    Button btLevel1 = new Button("Level 1");
    Button btLevel2 = new Button("Level 2");
    Button btLevel3 = new Button("Level 3");
    Label lbSeconds = new Label("Elapsed time : ");
    Label lbKillCount = new Label("Killcount : ");
    Text txtKillCount = new Text("");
    Text txtSeconds = new Text("");

    public ButtonBar() {
        super();
        setPadding(new Insets(5,5,5,5));
        getChildren().addAll(btStart, btPause, btLevel1, btLevel2, btLevel3, lbSeconds, txtSeconds, lbKillCount, txtKillCount);
        setAlignment(Pos.CENTER);
    }
}
