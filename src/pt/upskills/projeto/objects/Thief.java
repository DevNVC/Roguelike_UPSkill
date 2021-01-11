package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

public class Thief extends Enemy implements ImageTile {



    public Thief(Position position) {
        super(2, 10,3,150,position);
    }


    @Override
    public void moveEnemy() {
        int numeroAleatorio;
        numeroAleatorio = (int) (Math.random() * 4);
        Position newPosition = null;
        if(distance()>range) {
            switch (numeroAleatorio) {
                case 0:
                    newPosition = positionEnemy.plus(new Vector2D(1, 1));
                    break;
                case 1:
                    newPosition = positionEnemy.plus(new Vector2D(-1, -1));
                    break;
                case 2:
                    newPosition = positionEnemy.plus(new Vector2D(-1, 1));
                    break;
                case 3:
                    newPosition = positionEnemy.plus(new Vector2D(1, -1));
                    break;
            }
            if (validEnemyMove(newPosition)){
                positionEnemy = newPosition;}
        }
    }

    @Override
    public void enemyBecomesAgressive() {
        if (distance() <= range) {
            if (getPosition().getX() < Engine.hero.getHeroPosition().getX()) {
                newPosition = positionEnemy.plus(Direction.RIGHT.asVector());
            }
            if (getPosition().getX() > Engine.hero.getHeroPosition().getX()) {
                newPosition = positionEnemy.plus(Direction.LEFT.asVector());
            }
            if (getPosition().getY() < Engine.hero.getHeroPosition().getY()) {
                newPosition = positionEnemy.plus(Direction.DOWN.asVector());
            }
            if (getPosition().getY() > Engine.hero.getHeroPosition().getY()) {
                newPosition = positionEnemy.plus(Direction.UP.asVector());
            }
            enemyAttack(newPosition);
            if (validEnemyMove(newPosition)) {
                positionEnemy = newPosition;
            }
        }
    }

    @Override
    public String getName() {
        return "Thief";
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+","+getLife()+";";
    }
}
