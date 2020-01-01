package runestats;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("main method");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("start before fxml");
        VBox parent = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        System.out.println("start after fxml");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}