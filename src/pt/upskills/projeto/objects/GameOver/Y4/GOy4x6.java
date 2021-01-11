package pt.upskills.projeto.objects.GameOver.Y4;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy4x6 implements ImageTile {

    private Position position= new Position(6,6);

    @Override
    public String getName() {
        return "y4 (6)";
    }

    @Override
    public Position getPosition() {
        return position;
    }


}
