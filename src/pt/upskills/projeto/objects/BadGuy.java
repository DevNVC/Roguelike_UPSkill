package pt.upskills.projeto.objects;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class BadGuy extends Enemy implements ImageTile {



    public BadGuy(Position position) {
        super(3, 5,4,20,position);
    }





    @Override
    public String getName() {
        return "BadGuy";
    }


    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+","+getLife()+";";
    }


}
