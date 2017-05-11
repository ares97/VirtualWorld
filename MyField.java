import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class MyField extends GridPane implements Serializable {
    private Group root;
    private String name;
    private Color color;
    private int x;
    private int y;
    private boolean isEmpty;
    private Rectangle rectangle;
    private Organism organism;
    private Label label;
    public ContextMenu contextMenu;
    public static final int rectangleWidth = 25;
    public static final int rectangleHeight = 25;
    MenuItem sheep = new MenuItem(Organisms.SHEEP.name);
    MenuItem wolf = new MenuItem(Organisms.WOLF.name);
    MenuItem antelope = new MenuItem(Organisms.ANTELOPE.name);
    MenuItem fox = new MenuItem(Organisms.FOX.name);
    MenuItem tortoise = new MenuItem(Organisms.TORTOISE.name);
    MenuItem dandelion = new MenuItem(Organisms.DANDELION.name);
    MenuItem grass = new MenuItem(Organisms.GRASS.name);
    MenuItem guarana = new MenuItem(Organisms.GUARANA.name);
    MenuItem sosnowskyHogweed = new MenuItem(Organisms.SOSNOWSKY_HOGWEED.name);
    MenuItem wildBerry = new MenuItem(Organisms.WILD_BERRY.name);

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public void setColor(Color color) {
        this.color = color;
        rectangle.setStroke(color);
        rectangle.setFill(color.deriveColor(1, 1, 1, 0.7));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String charName = name.substring(0, 2);
        label.setText(charName);
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    private void prepareEmptyField(Group root, int x, int y, String name, Color color) {
        organism = null;
        this.root = root;
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
        isEmpty = true;
        rectangle = new Rectangle(rectangleWidth, rectangleHeight);
        rectangle.setStroke(color);
        rectangle.setFill(color.deriveColor(1, 1, 1, 0.7));
        label = new Label();
        setTranslateX(x * rectangleWidth);
        setTranslateY(y * rectangleHeight);
        getChildren().addAll(rectangle, label);
    }

    public MyField(Group root, String name, Color color, int x, int y) {

        prepareEmptyField(root, x, y, name, color);

        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(root, event.getScreenX(), event.getScreenY());
                }
            }
        });

        handleContextMenu();
    }

    void handleContextMenu() {
        contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(antelope, dandelion, fox, grass, guarana, sheep, sosnowskyHogweed, tortoise, wildBerry, wolf);
    }


}
