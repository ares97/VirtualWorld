import java.util.ArrayList;
import java.util.List;

public class Game {
    public List<Organism> organisms;
    private MyField[][] fields;

    Game(MyField[][] fields) {
        this.fields = fields;
        organisms = new ArrayList<>();
        setFirstOrganisms();
    }

    private void setFirstOrganisms() {
        //organisms.add(new Human(menuWindow, gameStage, gameScene, fields, organisms));
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
    }
}
