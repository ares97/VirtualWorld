import java.util.List;

public class WildBerry extends Plant {

    WildBerry(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.WILD_BERRY.strength, AllOrganisms.WILD_BERRY.initiation, AllOrganisms.WILD_BERRY.color, AllOrganisms.WILD_BERRY.name, organisms);
    }

    WildBerry(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.WILD_BERRY.strength, AllOrganisms.WILD_BERRY.initiation, AllOrganisms.WILD_BERRY.color, AllOrganisms.WILD_BERRY.name, organisms, posX, posY);
    }

    @Override
    int reflectedAttack(Organism opponent) {
        if (opponent.strength < 99)
            return 1;
        return 3;
    }

    @Override
    void action() {
        if (generator.nextInt(20) == 0) {
            MyField childField = getEmptyNearbyFieldForSpreadingPlant();
            if (childField != null) {
                organisms.add(new WildBerry(fields, organisms, childField.getY(), childField.getX()));
                announcements += (name + " spreads over\n");
            }
        }
    }
}
