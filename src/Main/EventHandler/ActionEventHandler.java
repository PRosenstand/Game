package Main.EventHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ActionEventHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        System.out.println("MORE POWAAAA");
    }
}
