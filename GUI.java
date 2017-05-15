import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends Application {
    public static Label announcements;
    public static KeyCode recentPressedKey;
    private StartingWindow startingWindow;
    private Stage firstStage;
    private int gameWidth;
    private int gameHeight;
    private Button nextTurn;
    private Button specialAbility;
    private Button saveGame;
    private VBox vBoxMenu;
    private Group gameRoot;
    private MyField[][] fields;
    private Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage firstStage) throws Exception {
        this.firstStage = firstStage;
        createStartingScene();
        handleFirstSceneButtons();
    }

    private void startGame() {
        game = new Game(fields);
        handleGameSceneButtons();
    }

    private void handleKeyArrows() {
        firstStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ((event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) && Human.isAlive) {
                    recentPressedKey = event.getCode();
                    Stage tempStage = new Stage();
                    tempStage.setScene(new Scene(new Label(recentPressedKey + " will be next human's directory")));
                    tempStage.show();
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            tempStage.hide();
                        }
                    }));
                    timeline.play();
                }
            }
        });
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

    private void handleFirstSceneButtons() {
        handleStartButton();
        handleLoadButton();
    }

    private void handleGameSceneButtons() {
        handleSaveButton();
        handleNextTurnButton();
        handleSpecialAbilityButton();
    }

    private void handleLoadButton() {
        startingWindow.LoadButton.setOnAction(event -> loadArchivedGame());
    }

    private void loadArchivedGame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load saved game");
        File savedGame = fileChooser.showOpenDialog(firstStage);

        try {
            LoadMaker loadMaker = new LoadMaker(savedGame, fields);
            createGameFromLoad(loadMaker);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createGameFromLoad(LoadMaker loadMaker) {
        loadMaker.readBoardSize();
        gameHeight = loadMaker.gameHeight;
        gameWidth = loadMaker.gameWidth;
        createGameSceneFromLoad();
        loadMaker.fields = fields;
        loadMaker.readOrganisms();
        game = new Game(fields, loadMaker.organisms);
        handleGameSceneButtons();
    }

    private void handleStartButton() {
        startingWindow.startButton.setOnAction(event -> {
            createGameScene();
            startGame();
            handleKeyArrows();
        });
    }

    private void handleNextTurnButton() {
        nextTurn.setOnAction(event -> Platform.runLater(() -> game.doTurn()));
    }

    private void handleSaveButton() {
        saveGame.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            fileChooser.setTitle("Choose path and save game");
            File whereToSave = fileChooser.showSaveDialog(firstStage);
            if (whereToSave == null)
                return;
            try {
                SaveMaker.saveOrganisms(whereToSave.getAbsolutePath(), gameWidth, gameHeight, game.organisms);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleSpecialAbilityButton() {
        specialAbility.setOnAction(event -> {
            if (Human.specialAbilityReadyToUse) {
                Human.useSpecialAbility = true;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Special ability is not ready to use, yet!");
                alert.showAndWait();
            }
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

    private void createGameSceneFromLoad() {
        gameRoot = new Group();
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
        specialAbility = new Button("special ability");
        return new ToolBar(nextTurn, specialAbility, saveGame);
    }

    private Label getAnnouncementsLabel() {
        announcements = new Label();
        announcements.setWrapText(true);
        announcements.setPrefHeight(gameHeight * MyField.rectangleHeight / 1.15);
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
        fields = new MyField[gameWidth][gameHeight];
        for (int i = 0; i < gameWidth; i++) {
            for (int j = 0; j < gameHeight; j++) {
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
                game.organisms.add(new Antelope(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].dandelion.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Dandelion(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].fox.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Fox(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].grass.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Grass(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].guarana.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Guarana(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].sheep.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Sheep(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].sosnowskyHogweed.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new SosnowskyHogweed(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].tortoise.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Tortoise(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].wildBerry.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new WildBerry(fields, game.organisms, finalI, finalJ));
            }
        });
        fields[i][j].wolf.setOnAction(event -> {
            if (fields[finalI][finalJ].isEmpty()) {
                game.organisms.add(new Wolf(fields, game.organisms, finalI, finalJ));
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
}
