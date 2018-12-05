import EventHandler.ActionEventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launch extends Application {


    public static void main(String[] args) {
        //Game game = new Game();
        launch(args);
        //game.play();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TEST");
        Button button = new Button();
        button.setText("SUPER POWAAAAAA");
        button.setOnAction(new ActionEventHandler());

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
