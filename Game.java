import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Game {
    public List<Organism> organisms;
    private MyField[][] fields;

    Game(MyField[][] fields) {
        this.fields = fields;
        organisms = new ArrayList<>();
        setFirstOrganisms();
    }

    Game(MyField[][] fields, List<Organism> organisms) {
        this.fields = fields;
        this.organisms = organisms;
    }

    private void setFirstOrganisms() {
        organisms.add(new Human(fields, organisms));
        organisms.add(new Wolf(fields, organisms));
        organisms.add(new Wolf(fields, organisms));
        organisms.add(new Fox(fields, organisms));
        organisms.add(new Fox(fields, organisms));
        organisms.add(new Tortoise(fields, organisms));
        organisms.add(new Tortoise(fields, organisms));
        organisms.add(new Antelope(fields, organisms));
        organisms.add(new Antelope(fields, organisms));
        organisms.add(new Sheep(fields, organisms));
        organisms.add(new Sheep(fields, organisms));
        organisms.add(new Grass(fields, organisms));
        organisms.add(new Dandelion(fields, organisms));
        organisms.add(new Guarana(fields, organisms));
        organisms.add(new WildBerry(fields, organisms));
        organisms.add(new SosnowskyHogweed(fields, organisms));
        sortOrganismsTurnQueue();
    }


    public void doTurn() {
        String announcements = "";
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).toDelete) {
                organisms.get(i).doTurn();
            } else {
                organisms.get(i).doTurn();
                announcements += organisms.get(i).announcements;
                GUI.announcements.setText(announcements);
            }
        }
        sortOrganismsTurnQueue();
    }


    private void sortOrganismsTurnQueue() {
        Collections.sort(organisms, new Comparator<Organism>() {
            @Override
            public int compare(Organism o1, Organism o2) {
                if (o1.initiation > o2.initiation)
                    return -1;
                else if (o1.initiation == o2.initiation && o1.age > o2.age)
                    return -1;
                else
                    return 1;
            }
        });
    }
}
