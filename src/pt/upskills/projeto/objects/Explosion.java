package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Explosion implements ImageTile {
    private Position position;

    public Explosion(Position position){this.position=position;}

    @Override
    public String getName() {
        return "ExplosionGif";
    }

    @Override
    public Position getPosition() {
        return position;
    }


}
