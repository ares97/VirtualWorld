import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UserInterface extends Application {
    private StartingWindow startingWindow;
    private Stage firstStage;
    private int gameWidth;
    private int gameHeight;
    private Button nextTurn;
    private Label announcements;
    private Button saveGame;
    private VBox vBoxMenu;
    private Group gameRoot;
    private MyField[][] fields;
    private Game game;

    @Override
    public void start(Stage firstStage) throws Exception {
        this.firstStage = firstStage;
        createStartingScene();
        handleButtonsOnAction();
    }

    private void startGame() {
        game = new Game(fields);
    }

    private void createStartingScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartingWindow.fxml"));
        Parent root = fxmlLoader.load();
        startingWindow = fxmlLoader.getController();
        firstStage.setTitle("Virtual World");
        Scene scene = new Scene(root);
        firstStage.setScene(scene);
        firstStage.show();
    }

    private void handleButtonsOnAction() {
        handleStartButton();
        handleLoadButton();
    }

    private void handleLoadButton() {
        startingWindow.LoadButton.setOnAction(event -> loadArchivedGame());
    }

    private void loadArchivedGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load saved game");
        File savedGame = fileChooser.showOpenDialog(firstStage);
    }

    private void handleStartButton() {
        startingWindow.startButton.setOnAction(event -> {
            createGameScene();
            startGame();
        });
    }

    private void createGameScene() {
        gameRoot = new Group();
        getAndSaveSize();
        addGameBoard();
        addMenuBar();
        firstStage.getScene().setRoot(gameRoot);
        firstStage.sizeToScene();
    }

    private void addMenuBar() {
        getAnnouncementsLabel();
        addVBoxMenu();
        gameRoot.getChildren().addAll(vBoxMenu);
    }

    private ToolBar getToolBar() {
        nextTurn = new Button("next turn");
        saveGame = new Button("save");
        return new ToolBar(nextTurn, saveGame);
    }

    private Label getAnnouncementsLabel() {
        announcements = new Label();
        announcements.setWrapText(true);
        announcements.setPrefHeight(gameHeight * MyField.rectangleHeight / 2);
        return announcements;
    }

    private void addVBoxMenu() {
        vBoxMenu = new VBox();
        vBoxMenu.setMinHeight(gameHeight * MyField.rectangleHeight);
        vBoxMenu.setMinWidth(MyField.rectangleWidth * 9);
        vBoxMenu.setStyle("-fx-background-color: khaki; -fx-font-size: 13;");
        vBoxMenu.setTranslateX(gameWidth * MyField.rectangleWidth);
        vBoxMenu.getChildren().addAll(getToolBar(), getAnnouncementsLabel());
    }

    private void addGameBoard() {
        fields = new MyField[gameHeight][gameWidth];
        for (int i = 0; i < gameHeight; i++) {
            for (int j = 0; j < gameWidth; j++) {
                MyField node = new MyField(gameRoot, "", Color.LIGHTGREEN, i, j);
                gameRoot.getChildren().add(node);
                fields[i][j] = node;
                handleAddingOrganismByRightClick(i, j);
            }
        }
    }

    private void handleAddingOrganismByRightClick(int i, int j) {
        int finalI = i;
        int finalJ = j;
        fields[i][j].antelope.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Antelope(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].dandelion.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Dandelion(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].fox.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Fox(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].grass.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Grass(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].guarana.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Guarana(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].sheep.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Sheep(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].sosnowskyHogweed.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new SosnowskyHogweed(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].tortoise.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Tortoise(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].wildBerry.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new WildBerry(fields, game.organisms, finalJ, finalI));
            }
        });
        fields[i][j].wolf.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Wolf(fields, game.organisms, finalJ, finalI));
            }
        });
    }

    private void getAndSaveSize() {
        try {
            gameWidth = Integer.parseInt(startingWindow.sizeX.getText());
            gameHeight = Integer.parseInt(startingWindow.sizeY.getText());
        } catch (NumberFormatException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("KEY IN ONLY NUMBERS!");
            alert.setTitle("Wrong size format!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
