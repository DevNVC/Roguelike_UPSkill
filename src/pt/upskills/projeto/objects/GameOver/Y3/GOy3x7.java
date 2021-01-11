package pt.upskills.projeto.objects.GameOver.Y3;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy3x7 implements ImageTile {

    private Position position= new Position(7,5);

    @Override
    public String getName() {
        return "y3 (7)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
