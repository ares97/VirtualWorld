import javafx.scene.paint.Color;

import java.util.List;

public class SosnowskyHogweed extends Plant {
    SosnowskyHogweed(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.SOSNOWSKY_HOGWEED.strength, AllOrganisms.SOSNOWSKY_HOGWEED.initiation, AllOrganisms.SOSNOWSKY_HOGWEED.color, AllOrganisms.SOSNOWSKY_HOGWEED.name, organisms);
    }

    SosnowskyHogweed(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.SOSNOWSKY_HOGWEED.strength, AllOrganisms.SOSNOWSKY_HOGWEED.initiation, AllOrganisms.SOSNOWSKY_HOGWEED.color, AllOrganisms.SOSNOWSKY_HOGWEED.name, organisms, posX, posY);
    }

    SosnowskyHogweed(MyField[][] fields, List<Organism> organisms, int posX, int posY, int str, int init, String name, Color color, boolean toDelete, int cooldown, int age) {
        super(fields, organisms, str, init, color, name, posX, posY, toDelete, age, cooldown);
    }

    @Override
    int reflectedAttack(Organism opponent) {
        if (opponent.strength < 10)
            return 1;
        return 3;
    }

    @Override
    void action() {
        if (generator.nextInt(20) == 0) {
            MyField childField = getEmptyNearbyFieldForSpreadingPlant();
            if (childField != null) {
                organisms.add(new SosnowskyHogweed(fields, organisms, childField.getX(), childField.getY()));
                announcements += (name + " spreads over\n");
            }
        }
        killOrganismsNearby();
    }

    void killOrganismsNearby() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (isInsideBoard(posX + j, posY + i) && !fields[posX + j][posY + i].isEmpty()) {
                    Organism opponent = fields[posX + j][posY + i].getOrganism();
                    if (opponent == null || opponent instanceof Plant)
                        continue;
                    opponent.emptyField();
                    opponent.toDelete = true;
                    announcements += (opponent.name + " is killed in " + name + "'s attack!\n");
                }
            }
        }
    }
}
