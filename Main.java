import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private StartingWindow startingWindow;
    private Stage firstStage;
    private Parent root;
    private int gameWidth;
    private int gameHeight;
    private Button nextTurn;
    private Button saveGame;
    private Group gameRoot;
    @Override
    public void start(Stage firstStage) throws Exception {
        this.firstStage = firstStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartingWindow.fxml"));
        root = fxmlLoader.load();
        startingWindow = fxmlLoader.getController();
        firstStage.setTitle("Virtual World");
        Scene scene = new Scene(root);
        firstStage.setScene(scene);
        firstStage.show();
        handleStartingWindowUI();
    }

    private void handleStartingWindowUI() {
        handleStartButton();
        //todo handle load button
    }
    private void handleStartButton(){
        startingWindow.startButton.setOnAction(event -> {
            createGameScene();
        });
    }
    private void createGameScene(){
        gameRoot = new Group();
        getAndSaveSize();
        addGameBoard();
        addMenuBar();
        firstStage.getScene().setRoot(gameRoot);
        firstStage.sizeToScene();
    }
    private void addMenuBar(){
        VBox vboxMenu = new VBox();
        nextTurn = new Button("next turn");
        saveGame = new Button("save");
        ToolBar toolBar = new ToolBar(nextTurn,saveGame);
        Label announcements = new Label("lorem loreml\nlorem loreml\nlorem loreml\nlorem loreml\nlorem loreml\n");
        announcements.setWrapText(true);
        announcements.setPrefHeight(gameHeight*MyField.rectangleHeight/2);
        vboxMenu.setMinHeight(gameHeight*MyField.rectangleHeight);
        vboxMenu.setMinWidth(MyField.rectangleWidth*9);
        vboxMenu.setStyle("-fx-background-color: khaki; -fx-font-size: 13;");
        vboxMenu.getChildren().addAll(toolBar,announcements);
        gameRoot.getChildren().addAll(vboxMenu);
        vboxMenu.setTranslateX(gameWidth*MyField.rectangleWidth);
    }
    private void addGameBoard(){
        for (int i = 0; i < gameWidth; i++) {
            for (int j = 0; j < gameHeight; j++) {
                MyField node = new MyField(gameRoot, "", Color.LIGHTGREEN, i, j);
                gameRoot.getChildren().add(node);
                //fields[i][j] = node;
            }
        }
    }
    private void getAndSaveSize(){
        try{
            gameWidth = Integer.parseInt(startingWindow.sizeX.getText());
            gameHeight = Integer.parseInt(startingWindow.sizeY.getText());
        }catch(NumberFormatException exc){
            Alert alert = new Alert(Alert.AlertType.ERROR );
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
