import javafx.scene.paint.Color;

import java.util.List;


public class Wolf extends Animal {

    Wolf(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.WOLF.strength, AllOrganisms.WOLF.initiation, AllOrganisms.WOLF.color, AllOrganisms.WOLF.name, organisms);
    }

    Wolf(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.WOLF.strength, AllOrganisms.WOLF.initiation, AllOrganisms.WOLF.color, AllOrganisms.WOLF.name, organisms, posX, posY);
    }

    Wolf(MyField[][] fields, List<Organism> organisms, int posX, int posY, int str, int init, String name, Color color, boolean toDelete, int cooldown, int age) {
        super(fields, organisms, str, init, color, name, posX, posY, toDelete, age, cooldown);
    }

    @Override
    void reproduction(Organism parent) {
        MyField emptyFieldForChild = getNearbyEmptyField(parent);
        if (emptyFieldForChild != null && (age >= 0 || parent.age >= 0)) {
            announcements += (name + " reproduces\n");
            Wolf child = new Wolf(fields, organisms, emptyFieldForChild.getX(), emptyFieldForChild.getY());
            organisms.add(child);
        }
    }

}
