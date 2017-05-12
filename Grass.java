import javafx.scene.paint.Color;

import java.util.List;

public class Grass extends Plant {
    Grass(MyField[][] fields, List<Organism> organisms) {
        super(fields, 0, 0, Color.GREEN, "Grass", organisms);
    }

    Grass(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, 0, 0, Color.GREEN, "Grass", organisms, posX, posY);
    }

    @Override
    void action() {
        if (generator.nextInt(20) == 0) {
            MyField childField = getEmptyNearbyFieldForSpreadingPlant();
            if (childField != null) {
                organisms.add(new Grass(fields, organisms, childField.getY(), childField.getX()));
                announcements += (name + " spreads over\n");
            }
        }
    }

    @Override
    int reflectedAttack(Organism opponent) {
        return 0;
    }
}
