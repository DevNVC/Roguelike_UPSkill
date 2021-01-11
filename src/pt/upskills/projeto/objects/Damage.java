package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Damage implements ImageTile {
    private Position position;

    public Damage(Position position){this.position=position;}

    @Override
    public String getName() {
        return "Damage";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
