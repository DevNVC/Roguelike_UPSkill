package pt.upskills.projeto.objects.status;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class RedGreenLifeBar extends LifeBar implements ImageTile {
    public RedGreenLifeBar(Position position) {
        super(position);
    }


    @Override
    public String getName() {
        return "RedGreen";
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
