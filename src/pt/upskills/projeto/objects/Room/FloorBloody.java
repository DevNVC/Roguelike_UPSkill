package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class FloorBloody implements ImageTile {

    private Position position;

    public FloorBloody(Position position) {
        this.position = position;
    }

    @Override
    public String getName() {
        return "FloorBloody";
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
