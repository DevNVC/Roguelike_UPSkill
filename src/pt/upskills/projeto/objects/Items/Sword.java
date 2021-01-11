package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Sword extends Weapon implements ImageTile {


    public Sword(Position position) {
        super(1,100,position);
    }

    @Override
    public String getName() {
        return "Sword";
    }

}
