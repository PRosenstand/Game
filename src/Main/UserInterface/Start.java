package Main.UserInterface;

import Main.GameObjects.Item;
import Main.GameObjects.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
    public static void main(String[] args) {
        Player player = new Player(null);
        player.addItem(new Item(null, 1, "test", "Test"));
        launch(args);
    }

    public void start(Stage primalStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primalStage.setTitle("Hello World");
        primalStage.setScene(new Scene(root, 300, 275));
        primalStage.show();
    }
}
