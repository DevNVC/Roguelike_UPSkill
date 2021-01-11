package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class LifePotion extends Food implements ImageTile {

    public LifePotion(Position position){
        super(4,10,position);
    }


    @Override
    public String getName() {
        return "LifePotion";
    }

}
