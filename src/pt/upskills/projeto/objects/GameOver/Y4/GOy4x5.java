package pt.upskills.projeto.objects.GameOver.Y4;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy4x5 implements ImageTile {

    private Position position= new Position(5,6);

    @Override
    public String getName() {
        return "y4 (5)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
