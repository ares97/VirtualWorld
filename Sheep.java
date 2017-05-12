import java.util.List;

public class Sheep extends Animal {
    Sheep(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.SHEEP.strength, AllOrganisms.SHEEP.initiation, AllOrganisms.SHEEP.color, AllOrganisms.SHEEP.name, organisms);
    }

    Sheep(MyField[][] fields, List<Organism> organisms, int posX, int posY) {
        super(fields, AllOrganisms.SHEEP.strength, AllOrganisms.SHEEP.initiation, AllOrganisms.SHEEP.color, AllOrganisms.SHEEP.name, organisms, posX, posY);
    }

    @Override
    void reproduction(Organism parent) {
        MyField emptyFieldForChild = getNearbyEmptyField(parent);
        if (emptyFieldForChild != null && (age >= 0 || parent.age >= 0)) {
            Sheep child = new Sheep(fields, organisms, emptyFieldForChild.getX(), emptyFieldForChild.getY());
            organisms.add(child);
            announcements += (name + " reproduces\n");
        }
    }
}
