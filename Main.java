import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Main extends Application {
    private StartingWindow startingWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartingWindow.fxml"));
        Parent root = fxmlLoader.load();
        startingWindow = fxmlLoader.getController();
        primaryStage.setTitle("Virtual World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        handleStartingWindowUI();
    }

    private void handleStartingWindowUI() {
        startingWindow.LoadButton.setOnAction(event -> {
            //TODO do load recent game
        });
        startingWindow.startButton.setOnAction(event -> {
            //TODO handle turning scene into gamefield
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
