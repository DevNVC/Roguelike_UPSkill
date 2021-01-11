package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Hammer extends Weapon implements ImageTile {


    public Hammer(Position position) {
        super(3,20,position);
    }

    @Override
    public String getName() {
        return "Hammer";
    }

}
