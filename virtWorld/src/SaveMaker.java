import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class SaveMaker {

    public static void saveOrganisms(String pathName, int gameWidth, int gameHeight, List<Organism> organisms) throws FileNotFoundException {
        File file = new File(pathName);
        PrintWriter output = new PrintWriter(file);
        output.println(gameWidth);
        output.println(gameHeight);
        for (Organism organism : organisms) {
            output.println(organism.name);
            output.println(organism.posX);
            output.println(organism.posY);
            output.println(organism.age);
            output.println(organism.strength);
            output.println(organism.initiation);
            output.println(organism.toDelete);
            output.println(organism.color);
            output.println(organism.cooldown);
        }
        output.close();
    }
}
