package Main.UserInterface;

import Main.Game;
import Main.GameObjects.Item;
import Main.GameObjects.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Start extends Application {
    public static List<Item> items = new ArrayList<>();
    public static Player player;
    public static void main(String[] args) {
        //launch(args);
        Game game = new Game();
        game.play();
        player = new Player(game.DungeonEntrance);
    }

    public void start(Stage primalStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primalStage.setTitle("Hello World");
        primalStage.setScene(new Scene(root, 300, 275));
        primalStage.show();
    }
}
