package pt.upskills.projeto.objects.GameOver.Y1;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

public class GOy1x1 implements ImageTile {

    private Position position= new Position(1,3);

    @Override
    public String getName() {
        return "y1 (1)";
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
