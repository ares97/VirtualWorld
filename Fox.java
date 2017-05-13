import javafx.scene.paint.Color;

import java.util.List;


public class Fox extends Animal {
    Fox(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.FOX.strength, AllOrganisms.FOX.initiation, AllOrganisms.FOX.color, AllOrganisms.FOX.name, organisms);
    }

    Fox(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.FOX.strength, AllOrganisms.FOX.initiation, AllOrganisms.FOX.color, AllOrganisms.FOX.name, organisms, posX, posY);
    }

    Fox(MyField[][] fields, List<Organism> organisms, int posX, int posY, int str, int init, String name, Color color, boolean toDelete, int cooldown, int age) {
        super(fields, organisms, str, init, color, name, posX, posY, toDelete, age, cooldown);
    }

    @Override
    void reproduction(Organism parent) {
        MyField emptyFieldForChild = getNearbyEmptyField(parent);
        if (emptyFieldForChild != null && (age >= 0 || parent.age >= 0)) {
            Fox child = new Fox(fields, organisms, emptyFieldForChild.getX(), emptyFieldForChild.getY());
            organisms.add(child);
            announcements += (name + " reproduces\n");
        }
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
                } else if (fields[posX + addToX][posY + addToY].getOrganism().strength > strength)
                    return; // 'fox never moves to stronger enemy's field
                else {
                    collision(fields[posX + addToX][posY + addToY].getOrganism());
                }
            }
        }
    }
}
