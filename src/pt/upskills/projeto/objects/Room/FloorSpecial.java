package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class FloorSpecial implements ImageTile {

    private Position position;

    public FloorSpecial(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "FloorSpecial";
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
