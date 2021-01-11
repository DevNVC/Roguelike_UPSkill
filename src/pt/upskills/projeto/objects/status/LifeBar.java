package pt.upskills.projeto.objects.status;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.List;

public class LifeBar {

    protected Position position;

    public Position getPosition() {
        return position;
    }

    public LifeBar(Position position){
        this.position=position;
    }


}
