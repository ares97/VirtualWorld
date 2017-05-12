import java.util.List;

public class Guarana extends Plant {
    Guarana(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.GUARANA.strength, AllOrganisms.GUARANA.initiation, AllOrganisms.GUARANA.color, AllOrganisms.GUARANA.name, organisms);
    }

    Guarana(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.GUARANA.strength, AllOrganisms.GUARANA.initiation, AllOrganisms.GUARANA.color, AllOrganisms.GUARANA.name, organisms, posX, posY);
    }

    @Override
    void action() {
        if (generator.nextInt(20) == 0) {
            MyField childField = getEmptyNearbyFieldForSpreadingPlant();
            if (childField != null) {
                organisms.add(new Guarana(fields, organisms, childField.getY(), childField.getX()));
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
