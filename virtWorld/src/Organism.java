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
        else {
            announcements = "";
            action();
            age++;
        }
    }

    abstract int reflectedAttack(Organism opponent);

    public void emptyField() {
        fields[posX][posY].setEmpty(true);
        fields[posX][posY].setName("  ");
        fields[posX][posY].setColor(Color.LIGHTGREEN);
        fields[posX][posY].setOrganism(null);
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
            fields[posX][posY].setOrganism(this);
            fields[posX][posY].setName(name);
            fields[posX][posY].setColor(color);
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

    Organism(MyField fields[][], List<Organism> organisms, int str, int init, Color color, String name, int posX, int posY, boolean toDelete, int age, int cooldown) {
        this.fields = fields;
        strength = str;
        initiation = init;
        announcements = "";
        this.age = age;
        this.toDelete = toDelete;
        this.color = color;
        this.name = name;
        generator = new Random();
        this.organisms = organisms;
        this.posX = posX;
        this.posY = posY;
        this.cooldown = cooldown;
        setOnField(posX, posY);
    }

    public void setOnField(int destX, int destY) {
        try {
            posX = destX;
            posY = destY;
            fields[destX][destY].setName(name);
            fields[destX][destY].setEmpty(false);
            fields[destX][destY].setOrganism(this);
            fields[destX][destY].setColor(color);
        } catch (IndexOutOfBoundsException exc) {
            exc.printStackTrace();
        }
    }

    public boolean isFieldEmpty(int x, int y) {
        return fields[x][y].isEmpty();
    }

    public boolean setOrganismOnEmptyField() {
        Random generator = new Random();
        int x = generator.nextInt(fields.length);
        int y = generator.nextInt(fields[0].length);
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if (isFieldEmpty((x + i) % fields.length, (y + j) % fields[0].length)) {
                    posY = (y + j) % fields[0].length;
                    posX = (x + i) % fields.length;
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
