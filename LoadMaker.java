import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadMaker {
    MyField[][] fields;
    List<Organism> organisms;
    int gameWidth, gameHeight;
    Scanner input;
    File fileToReadFrom;

    LoadMaker(File fileToReadFrom, MyField[][] fields) throws FileNotFoundException {
        input = new Scanner(fileToReadFrom);
        this.fields = fields;
        this.fileToReadFrom = fileToReadFrom;
    }


    public void readBoardSize() {
        gameWidth = input.nextInt();
        gameHeight = input.nextInt();
        input.nextLine();
    }

    public void readOrganisms() {
        organisms = new ArrayList<>();
        String name;
        int posX, posY, age, strenght, initiation, cooldown;
        boolean toDelete;
        Color color;
        while (input.hasNext()) {
            name = input.nextLine();
            posX = Integer.parseInt(input.nextLine());
            posY = Integer.parseInt(input.nextLine());
            age = Integer.parseInt(input.nextLine());
            strenght = Integer.parseInt(input.nextLine());
            initiation = Integer.parseInt(input.nextLine());
            toDelete = Boolean.parseBoolean(input.nextLine());
            color = Color.valueOf(input.nextLine());
            cooldown = Integer.parseInt(input.nextLine());
            switch (name) {
                case "Wolf":
                    organisms.add(new Wolf(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Wild Berry":
                    organisms.add(new WildBerry(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Tortoise":
                    organisms.add(new Tortoise(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Sosnowsky's Hogweed":
                    organisms.add(new SosnowskyHogweed(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Sheep":
                    organisms.add(new Sheep(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Human":
                    organisms.add(new Human(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Guarana":
                    organisms.add(new Guarana(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Grass":
                    organisms.add(new Grass(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Fox":
                    organisms.add(new Fox(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Dandelion":
                    organisms.add(new Dandelion(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;
                case "Antelope":
                    organisms.add(new Antelope(fields, organisms, posX, posY, strenght, initiation, name, color, toDelete, cooldown, age));
                    break;


            }
        }
        input.close();
    }

}
