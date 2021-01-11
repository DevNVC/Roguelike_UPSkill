package pt.upskills.projeto.objects.status;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class RedLifeBar extends LifeBar implements ImageTile {


    public RedLifeBar(Position position) {
        super(position);
    }

    public Position setPosition(int x, int y){
        super.position= new Position(x,y);
        return position;
    }


    @Override
    public String getName() {
        return "Red";
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
