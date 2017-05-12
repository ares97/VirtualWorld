import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public abstract class Organism {
    protected String announcements;
    protected int age;
    protected int strength;
    protected int initiation;
    protected int posX, posY;
    protected boolean toDelete;
    public final Color color;
    public final String name;
    protected MyField fields[][];

    protected Random generator;
    protected List organisms;
    protected int cooldown;

    abstract void action();

    void doTurn() {
        if (toDelete)
            organisms.remove(this);
        announcements = "";
        action();
        age++;
    }

    abstract int reflectedAttack(Organism opponent);

    public void emptyField() {
        fields[posY][posX].setEmpty(true);
        fields[posY][posX].setName("  ");
        fields[posY][posX].setColor(Color.LIGHTGREEN);
        fields[posY][posX].setOrganism(null);
    }

    Organism(MyField fields[][], int str, int init, Color color, String name, List<Organism> organisms) {
        this.fields = fields;
        announcements = "";
        strength = str;
        initiation = init;
        age = 0;
        toDelete = false;
        this.color = color;
        this.name = name;
        generator = new Random();
        this.organisms = organisms;
        if (setOrganismOnEmptyField()) {
            fields[posY][posX].setOrganism(this);
            fields[posY][posX].setName(name);
            fields[posY][posX].setColor(color);
        }
    }

    Organism(MyField fields[][], int str, int init, Color color, String name, List<Organism> organisms, int posX, int posY) {
        this.fields = fields;
        strength = str;
        initiation = init;
        announcements = "";
        age = 0;
        toDelete = false;
        this.color = color;
        this.name = name;
        generator = new Random();
        this.organisms = organisms;
        this.posX = posX;
        this.posY = posY;
        setOnField(posX, posY);
    }

    public void setOnField(int destX, int destY) {
        try {
            posX = destX;
            posY = destY;
            fields[destY][destX].setColor(color);
            fields[destY][destX].setName(name);
            fields[destY][destX].setEmpty(false);
            fields[destY][destX].setOrganism(this);
        } catch (IndexOutOfBoundsException exc) {
            exc.printStackTrace();
        }
    }

    public boolean isFieldEmpty(int x, int y) {
        return fields[y][x].isEmpty();
    }

    public boolean setOrganismOnEmptyField() {
        Random generator = new Random();
        int x = generator.nextInt(fields.length);
        int y = generator.nextInt(fields[0].length);
        for (int i = 0; i < fields[0].length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (isFieldEmpty((x + j) % fields.length, (y + i) % fields[0].length)) {
                    posX = (x + j) % fields.length;
                    posY = (y + i) % fields[0].length;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < fields.length && y >= 0 && y < fields[0].length;
    }
}
