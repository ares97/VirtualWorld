import javafx.scene.paint.Color;

import java.util.List;


public abstract class Plant extends Organism {

    Plant(MyField[][] fields, int str, int init, Color color, String name, List<Organism> organisms) {
        super(fields, str, 0, color, name, organisms);
    }

    Plant(MyField[][] fields, int str, int init, Color color, String name, List<Organism> organisms, int posX, int posY) {
        super(fields, str, 0, color, name, organisms, posX, posY);
    }

    public MyField getEmptyNearbyFieldForSpreadingPlant() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isInsideBoard((posX + (j - 1)), (posY + (i - 1))) &&
                        isFieldEmpty((posX + (j - 1)), (posY + (i - 1)))) {
                    return fields[(posY + i - 1)][(posX + j - 1)];
                }
            }
        }
        return null;
    }

    @Override
    void action() {
        return;
    }

}
