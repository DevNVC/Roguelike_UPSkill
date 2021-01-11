package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.Room.RoomElement;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Item implements ImageTile {

    protected Position position;
    public static List<ImageTile> items = new ArrayList<>();
    private int scoreValue;
    private static int scoreCountItem;
    protected static boolean position0 = false;
    protected static boolean position1 = false;
    protected static boolean position2 = false;
    protected Position zero = new Position(7, 0);
    protected Position one = new Position(8, 0);
    protected Position two = new Position(9, 0);

    public Item(int scoreValue, Position position) {
        this.scoreValue = scoreValue;
        this.position = position;
    }

    public List getItems() {
        return items;
    }

    public void resetItems() {
        position0 = false;
        position1 = false;
        position2 = false;
        items.clear();
        scoreCountItem = 0;
    }

    public void addWeaponDamage(ImageTile tile) {
        if (tile instanceof Weapon) {
            Engine.hero.setDamage(Engine.hero.getDamage() + ((Weapon) tile).getWeaponDamage());
        }
    }

    public void removeWeaponDamage(ImageTile tile) {
        Engine.hero.setDamage(Engine.hero.getDamage() - ((Weapon) tile).getWeaponDamage());

    }

    public void addScoreCountItem() {
        scoreCountItem += scoreValue;
    }

    public static int getScoreCountItem() {
        return scoreCountItem;
    }

    public void setPosition(Position newPosition) {
        position = newPosition;
    }

    public void addItem(ImageTile tile, Position newPosition) {
        items.add(tile);
        for (ImageTile i : Engine.tiles) {
            if (i instanceof Item) {
                if (i.equals(tile)) {
                    if (i.getPosition().equals(tile.getPosition())) {
                        ((Item) i).setPosition(newPosition);
                        Engine.removeImage(i);
                        break;
                    }
                }
            }
        }
        Engine.addNewStatusImage(tile);
        addScoreCountItem();
        items.add(tile);
    }


    public void checkIfAddItem(ImageTile tile) {

        if (!position0) {
            addItem(tile, zero);
            position0 = true;
            return;
        } else if (!position1) {
            addItem(tile, one);
            position1 = true;
            return;
        } else if (!position2) {
            addItem(tile, two);
            position2 = true;
            return;
        }
    }


    public boolean validateCanDropItem() {
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof RoomElement) {
                if (tile.getPosition().equals(Engine.hero.getHeroPosition().plus(Engine.hero.getLastDirection().asVector()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void dropItem(ImageTile tile) {
        for (ImageTile item : items) {
            if (tile.equals(item)) {
                if (tile.getPosition().equals(zero)) {
                    position0 = false;
                }
                if (tile.getPosition().equals(one)) {
                    position1 = false;
                }
                if (tile.getPosition().equals(two)) {
                    position2 = false;
                }
            }
        }
        Engine.removeStatusImage(tile);
        items.remove(tile);
        Engine.addNewImage(tile);
    }


    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getName() + "," + getPosition().getX() + "," + getPosition().getY() + ";";
    }
}
