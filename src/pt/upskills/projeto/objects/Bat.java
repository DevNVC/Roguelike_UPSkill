package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Room.Door;
import pt.upskills.projeto.objects.Room.RoomElement;
import pt.upskills.projeto.objects.Room.Water;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

public class Bat extends Enemy implements ImageTile {



    public Bat(Position position) {
        super(1, 3,1,10,position);
    }

    @Override
    public String getName() {
        return "Bat";
    }


    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+","+getLife()+";";
    }


    @Override
    public boolean validEnemyMove(Position newPositionToValidate) {
        for(ImageTile a : Engine.tiles) {
            if (a instanceof RoomElement ||a instanceof Door || a instanceof Enemy|| a instanceof Hero) {
                if (a.getPosition().equals(newPositionToValidate)) {
                    return false;
                }
            }
        }return true;
    }

    Position position1 = null;
    Position position2=null;
    Position position3=null;
    Position position4=null;
    Position position5=null;
    Position position6=null;
    Position position7=null;
    Position miniPosition1=null;
    Position miniPosition2=null;
    Position miniPosition3=null;
    private final Position original =getPosition();
    @Override
    public void moveEnemy() {
        Position newPosition = null;
        if(distance()>super.getRange()){
            if(validEnemyMove(original.plus(new Vector2D(2,0))) ||
                    validEnemyMove(original.plus(new Vector2D(-2,0)))) {
                if (position1 == null && position3 != null) {
                    newPosition = getPosition().plus(new Vector2D(1, 0));
                    position3 = null;
                    position7 = null;
                } else if (position1 == null) {
                    newPosition = getPosition().plus(new Vector2D(1, 0));
                    position1 = newPosition;
                } else if (position2 == null) {
                    newPosition = getPosition().plus(new Vector2D(1, 0));
                    position2 = newPosition;
                } else if (position3 == null) {
                    newPosition = getPosition().plus(new Vector2D(-1, 0));
                    position3 = newPosition;
                } else if (position4 == null) {
                    newPosition = getPosition().plus(new Vector2D(-1, 0));
                    position4 = newPosition;
                } else if (position5 == null) {
                    newPosition = getPosition().plus(new Vector2D(-1, 0));
                    position5 = newPosition;
                } else if (position6 == null) {
                    newPosition = getPosition().plus(new Vector2D(-1, 0));
                    position6 = newPosition;
                } else if (position7 == null) {
                    newPosition = getPosition().plus(new Vector2D(1, 0));
                    position7 = newPosition;
                    position1 = null;
                    position2 = null;
                    position4 = null;
                    position5 = null;
                    position6 = null;
                }
            }else
            if(!validEnemyMove(original.plus(new Vector2D(2,0))) ||
                    !validEnemyMove(original.plus(new Vector2D(-2,0)))) {
                if (miniPosition1 == null && miniPosition3 != null) {
                    newPosition = getPosition().plus(new Vector2D(1, 0));
                    miniPosition3 = null;
                } else if (miniPosition1 == null) {
                    newPosition = getPosition().plus(new Vector2D(1, 0));
                    miniPosition1 = newPosition;

                } else if (miniPosition2 == null) {
                    newPosition = getPosition().plus(new Vector2D(-1, 0));
                    miniPosition2 = newPosition;
                } else if (miniPosition3 == null) {
                    newPosition = getPosition().plus(new Vector2D(-1, 0));
                    miniPosition3 = newPosition;
                    miniPosition1=null;
                    miniPosition2=null;
                }
            }
            enemyAttack(newPosition);
            if(super.validEnemyMove(newPosition)){
                positionEnemy=newPosition;
            }

        }
    }

}
