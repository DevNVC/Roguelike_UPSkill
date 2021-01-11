package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class FloorSewer implements ImageTile {

    private Position position;

    public FloorSewer(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "FloorSewer";
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
