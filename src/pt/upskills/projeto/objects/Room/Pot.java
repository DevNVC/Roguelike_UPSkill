package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Pot extends RoomElement implements ImageTile {

    private Position position;

    public Pot(Position position){this.position=position;}

    public void drinkFromThePot(){}


    @Override
    public String getName() {
        return null;
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
