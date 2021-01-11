package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class FloorBoss implements ImageTile {

    private Position position;

    public FloorBoss(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "FloorBoss";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+";";
    }
}
