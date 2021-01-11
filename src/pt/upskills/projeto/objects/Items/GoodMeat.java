package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GoodMeat extends Food implements ImageTile {

    public GoodMeat(Position position){
        super(2,3,position);
    }


    @Override
    public String getName() {
        return "GoodMeat";
    }


}
