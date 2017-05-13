import javafx.scene.paint.Color;

import java.util.List;

public class Guarana extends Plant {
    Guarana(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.GUARANA.strength, AllOrganisms.GUARANA.initiation, AllOrganisms.GUARANA.color, AllOrganisms.GUARANA.name, organisms);
    }

    Guarana(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.GUARANA.strength, AllOrganisms.GUARANA.initiation, AllOrganisms.GUARANA.color, AllOrganisms.GUARANA.name, organisms, posX, posY);
    }

    Guarana(MyField[][] fields, List<Organism> organisms, int posX, int posY, int str, int init, String name, Color color, boolean toDelete, int cooldown, int age) {
        super(fields, organisms, str, init, color, name, posX, posY, toDelete, age, cooldown);
    }

    @Override
    void action() {
        if (generator.nextInt(20) == 0) {
            MyField childField = getEmptyNearbyFieldForSpreadingPlant();
            if (childField != null) {
                organisms.add(new Guarana(fields, organisms, childField.getX(), childField.getY()));
                announcements += (name + " spreads over\n");
            }
        }
    }

    @Override
    int reflectedAttack(Organism opponent) {
        opponent.strength += 3;
        return 0;
    }
}
