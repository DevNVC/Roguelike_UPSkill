package pt.upskills.projeto.objects.status;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GreenLifeBar extends LifeBar implements ImageTile {

    public GreenLifeBar(Position position) {
        super(position);
    }


    @Override
    public String getName() {
        return "Green";
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }


    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+";";
    }
}
