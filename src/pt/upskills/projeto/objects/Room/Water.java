package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Water implements ImageTile {
    private Position position;

    public Water(Position position){
        this.position=position;
    }

    public void evaporate(ImageTile water){
        Engine.tiles.remove(water);
        Engine.gui.removeImage(water);
        Engine.gui.update();
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+";";
    }
}
