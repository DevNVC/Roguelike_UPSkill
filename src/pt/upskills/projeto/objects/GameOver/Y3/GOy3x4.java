package pt.upskills.projeto.objects.GameOver.Y3;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class GOy3x4 implements ImageTile {

    private Position position= new Position(4,5);

    @Override
    public String getName() {
        return "y3 (4)";
    }

    @Override
    public Position getPosition() {
        return position;
    }


}
