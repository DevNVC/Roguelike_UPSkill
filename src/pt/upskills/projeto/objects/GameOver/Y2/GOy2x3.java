package pt.upskills.projeto.objects.GameOver.Y2;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy2x3 implements ImageTile {

    private Position position= new Position(3,4);

    @Override
    public String getName() {
        return "y2 (3)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
