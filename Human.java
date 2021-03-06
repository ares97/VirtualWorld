import javafx.scene.paint.Color;

import java.util.List;

public class Human extends Animal {
    static boolean useSpecialAbility;
    static boolean specialAbilityReadyToUse;
    static boolean isAlive;

    Human(MyField[][] fields, List<Organism> organisms) {
        super(fields, AllOrganisms.HUMAN.strength, AllOrganisms.HUMAN.initiation, AllOrganisms.HUMAN.color, AllOrganisms.HUMAN.name, organisms);
        super.action();
        addToX = 0;
        addToY = 0;
        cooldown = 0;
        useSpecialAbility = false;
        specialAbilityReadyToUse = true;
        isAlive = true;
    }

    Human(MyField[][] fields, List<Organism> organisms, int posX, int posY, int str, int init, String name, Color color, boolean toDelete, int cooldown, int age) {
        super(fields, organisms, str, init, color, name, posX, posY, toDelete, age, cooldown);
        if (cooldown != 0) {
            useSpecialAbility = false;
            specialAbilityReadyToUse = false;
            isAlive = true;
        }
    }

    @Override
    void reproduction(Organism parent) {
    }

    void doTurn() {
        if (toDelete) {
            emptyField();
            isAlive = false;
            organisms.remove(this);
        } else {
            announcements = "";
            handleSpecialAbility();
            action();
            age++;
        }
    }

    private void moveHuman(int x, int y) {
        addToY = 0;
        addToX = 0;
        addToY = y;
        addToX = x;
    }

    @Override
    void setMovePosition() {
        addToY = 0;
        addToX = 0;
        if (GUI.recentPressedKey != null) {
            switch (GUI.recentPressedKey) {
                case UP:
                    moveHuman(0, -1);
                    break;
                case DOWN:
                    moveHuman(0, 1);
                    break;
                case RIGHT:
                    moveHuman(1, 0);
                    break;
                case LEFT:
                    moveHuman(-1, 0);
                    break;
            }
        }
        GUI.recentPressedKey = null;
    }

    private void handleSpecialAbility() {
        if (cooldown == 0 && useSpecialAbility) {
            specialAbilityReadyToUse = false;
            useSpecialAbility = false;
            cooldown = 5;
            strength += cooldown;
            announcements += (name + " used special ability and has \n" + strength + " strength!\n");
        } else if (cooldown > 0) {
            strength--;
            cooldown--;
        }
        if (cooldown == 0)
            specialAbilityReadyToUse = true;
    }

}