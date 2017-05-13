import javafx.scene.paint.Color;

import java.util.List;

public class Tortoise extends Animal {
    Tortoise(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.TORTOISE.strength, AllOrganisms.TORTOISE.initiation, AllOrganisms.TORTOISE.color, AllOrganisms.TORTOISE.name, organisms);
    }

    Tortoise(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.TORTOISE.strength, AllOrganisms.TORTOISE.initiation, AllOrganisms.TORTOISE.color, AllOrganisms.TORTOISE.name, organisms, posX, posY);
    }

    Tortoise(MyField[][] fields, List<Organism> organisms, int posX, int posY, int str, int init, String name, Color color, boolean toDelete, int cooldown, int age) {
        super(fields, organisms, str, init, color, name, posX, posY, toDelete, age, cooldown);
    }

    @Override
    int reflectedAttack(Organism opponent) {
        if (opponent.strength < 5) {
            return -1;
        }
        return 0;
    }

    @Override
    void reproduction(Organism parent) {
        MyField emptyFieldForChild = getNearbyEmptyField(parent);
        if (emptyFieldForChild != null && (age >= 0 || parent.age >= 0)) {
            announcements += (name + " reproduces\n");
            Tortoise child = new Tortoise(fields, organisms, emptyFieldForChild.getX(), emptyFieldForChild.getY());
            organisms.add(child);
        }
    }

    @Override
    void setMovePosition() {
        addToX = 0;
        addToY = 0;
        if (generator.nextInt(4) == 1) {
            if (generator.nextBoolean())
                addToX = getRandomMovement();
            else
                addToY = getRandomMovement();
        }
    }
}
