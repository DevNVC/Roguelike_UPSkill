package pt.upskills.projeto.objects.GameOver.Y4;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy4x3 implements ImageTile {

    private Position position= new Position(3,6);

    @Override
    public String getName() {
        return "y4 (3)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
