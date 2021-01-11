package pt.upskills.projeto.objects.GameOver.Y1;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy1x6 implements ImageTile {

    private Position position= new Position(6,3);

    @Override
    public String getName() {
        return "y1 (6)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
