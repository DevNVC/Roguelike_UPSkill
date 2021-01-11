package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class WaterDark extends Water implements ImageTile {

    public WaterDark(Position position){
        super(position);
    }

    @Override
    public String getName() {
        return "WaterDark";
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
