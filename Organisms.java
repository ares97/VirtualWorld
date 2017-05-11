import javafx.scene.paint.Color;

public enum Organisms {
    ANTELOPE(4, 4, "Antelope", Color.CORNSILK),
    DANDELION(0, 0, "Dandelion", Color.YELLOW),
    FOX(3, 7, "Fox", Color.DARKRED),
    GRASS(0, 0, "Grass", Color.GREEN),
    GUARANA(0, 0, "Guarana", Color.ORANGERED),
    HUMAN(5, 4, "Human", Color.TAN),
    SHEEP(4, 4, "Sheep", Color.WHITESMOKE),
    SOSNOWSKY_HOGWEED(10, 0, "Sosnowsky's Hogweed", Color.MEDIUMSEAGREEN),
    TORTOISE(2, 1, "Tortoise", Color.BROWN),
    WILD_BERRY(99, 0, "Wild Berry", Color.DARKVIOLET),
    WOLF(9, 5, "Wolf", Color.DARKGRAY);

    final int strength, initiation;
    final String name;
    final Color color;

    Organisms(int strength, int initiation, String name, Color color) {
        this.strength = strength;
        this.initiation = initiation;
        this.name = name;
        this.color = color;
    }
}
