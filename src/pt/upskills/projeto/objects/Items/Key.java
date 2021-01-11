package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Position;

public class Key extends Item implements ImageTile {

    public Key(Position position) {
        super(30, position);
    }

    public boolean canUnlock() {
        for (ImageTile tile : Status.status) {
            if (tile instanceof Key) {
                return true;
            }
        }
        return false;
    }

    public void unlock() {
        for (ImageTile tile : Status.status) {
            if (tile instanceof Key) {
                if (tile.getPosition().equals(zero)) {
                    position0 = false;
                }
                if (tile.getPosition().equals(one)) {
                    position1 = false;
                }
                if (tile.getPosition().equals(two)) {
                    position2 = false;
                }
                Engine.removeStatusImage(tile);
                items.remove(tile);
                Engine.gui.clearStatus();
                Engine.gui.newStatusImages(Status.status);
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "Key";
    }


}
