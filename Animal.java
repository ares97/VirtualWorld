import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public abstract class Animal extends Organism {
    protected int addToX, addToY;

    Animal(MyField[][] fields, int str, int init, Color color, String name, List<Organism> organisms) {
        super(fields, str, init, color, name, organisms);
        addToX = 0;
        addToY = 0;
    }

    Animal(MyField[][] fields, int str, int init, Color color, String name, List<Organism> organisms, int posX, int posY) {
        super(fields, str, init, color, name, organisms, posX, posY);
        addToX = 0;
        addToY = 0;
    }

    void collision(Organism opponent) {
        if (this.getClass().isInstance(opponent)) {
            reproduction(opponent);
        } else {
            if (opponent.reflectedAttack(this) == 0) { // attacker won
                opponent.emptyField();
                opponent.toDelete = true;
                emptyField();
                setOnField(opponent.posX, opponent.posY);
                announcements += (name + " kills " + opponent.name + "\n");
            } else if (opponent.reflectedAttack(this) == 1) {// attacker lost
                emptyField();
                toDelete = true;
                announcements += (name + " is killed by " + opponent.name + "\n");
            } else if (opponent.reflectedAttack(this) == 2) { // i.e antelope case
                emptyField();
                setOnField(opponent.posX, opponent.posY);
                announcements += (opponent.name + " escapes");
            } else if (opponent.reflectedAttack(this) == 3) { // i.e sonsowsky hogweeed case
                opponent.emptyField();
                opponent.toDelete = true;
                emptyField();
                toDelete = true;
                announcements += (name + " eats " + opponent.name + " and dies!");
            }
        }
    }

    abstract void reproduction(Organism parent);

    MyField getNearbyEmptyField(Organism parent) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isInsideBoard(posX + (j - 1), posY + (i - 1)) &&
                        isFieldEmpty(posX + (j - 1), posY + (i - 1))) {
                    return fields[posX + (j - 1)][posY + (i - 1)];
                } else if (isInsideBoard(parent.posX + (j - 1), parent.posY + (i - 1)) &&
                        isFieldEmpty(parent.posX + (j - 1), parent.posY + (i - 1))) {
                    return fields[parent.posX + (j - 1)][parent.posY + (i - 1)];
                }
            }
        }
        return null;
    }

    @Override
    int reflectedAttack(Organism opponent) {
        if (strenght > opponent.strenght)
            return 1;
        else if (strenght == opponent.strenght) {
            if (age > opponent.age)
                return 1;
        }
        return 0;
    }

    @Override
    void action() {
        if (!toDelete && age >= 0) {
            setMovePosition();
            if (addToX == 0 && addToY == 0) return;
            if (isInsideBoard(posX + addToX, posY + addToY)) {
                if (isFieldEmpty(posX + addToX, posY + addToY)) {
                    emptyField();
                    setOnField(posX + addToX, posY + addToY);
                } else {
                    collision(fields[posY + addToY][posX + addToX].getOrganism());
                }
            }
        }
    }

    void setMovePosition() {
        addToX = 0;
        addToY = 0;
        if (generator.nextBoolean())
            addToX = getRandomMovement();
        else
            addToY = getRandomMovement();
    }

    public int getRandomMovement() { //
        Random generator = new Random();
        int a = 0;
        while (a == 0)
            a = generator.nextInt(3) - 1;
        return a;
    }


}
