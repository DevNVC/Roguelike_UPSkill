package pt.upskills.projeto.objects.GameOver.Y4;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy4x2 implements ImageTile {

    private Position position= new Position(2,6);

    @Override
    public String getName() {
        return "y4 (2)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
