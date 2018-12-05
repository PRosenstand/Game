package Main.UserInterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primalStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primalStage.setTitle("Hello World");
        primalStage.setScene(new Scene(root, 300, 275));
        primalStage.show();
    }
}
