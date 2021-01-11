package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Room.Door;
import pt.upskills.projeto.objects.Room.RoomElement;
import pt.upskills.projeto.objects.Room.Water;
import pt.upskills.projeto.rogue.utils.Position;

public class Tengu extends Enemy implements ImageTile {



    public Tengu(Position position) {
        super(3, 20,4,1000,position);
    }


    @Override
    public void enemyAttack(Position newAttackPosition) {
        super.enemyAttack(newAttackPosition);
    }

    @Override
    public String getName() {
        return "Tengu";
    }

    @Override
    public boolean validEnemyMove(Position newPositionToValidate) {
        for(ImageTile a : Engine.tiles) {
            if (a instanceof RoomElement || a instanceof Door || a instanceof Enemy|| a instanceof Hero) {
                if (a.getPosition().equals(newPositionToValidate)) {
                    return false;
                }
            }
        }return true;
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+","+getLife()+";";
    }
}
