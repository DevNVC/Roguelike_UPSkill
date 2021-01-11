package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class WallSewers extends RoomElement implements ImageTile {
    private Position position;

    public WallSewers(Position position){
        this.position=position;
    }


    @Override
    public String getName() {
        return "WallSewers"; //nome da imagem do ImageTile
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
