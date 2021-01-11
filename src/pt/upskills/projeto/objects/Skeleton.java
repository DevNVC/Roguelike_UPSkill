package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Room.Door;
import pt.upskills.projeto.objects.Room.RoomElement;
import pt.upskills.projeto.objects.Room.Water;
import pt.upskills.projeto.rogue.utils.Position;

public class Skeleton extends Enemy implements ImageTile {



    public Skeleton(Position position) {
        super(1, 2,3,10,position);
    }

    @Override
    public String getName() {
        return "Skeleton";
    }

    @Override
    public boolean validEnemyMove(Position newPositionToValidate) {
        for(ImageTile a : Engine.tiles) {
            if (a instanceof RoomElement || a instanceof Door || a instanceof Water || a instanceof Enemy|| a instanceof Hero) {
                if (a.getPosition().equals(newPositionToValidate)) {
                    return false;
                }
            }
        }return true;
    }

}
