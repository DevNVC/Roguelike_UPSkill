package pt.upskills.projeto.objects.GameOver.Y4;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy4x8 implements ImageTile {

    private Position position= new Position(8,6);

    @Override
    public String getName() {
        return "y4 (8)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
