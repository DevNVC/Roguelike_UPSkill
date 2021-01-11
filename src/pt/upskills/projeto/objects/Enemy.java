package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Items.FireballPotion;
import pt.upskills.projeto.objects.Items.GoodMeat;
import pt.upskills.projeto.objects.Items.LifePotion;
import pt.upskills.projeto.objects.Items.Sword;
import pt.upskills.projeto.objects.Room.Barricade;
import pt.upskills.projeto.objects.Room.Door;
import pt.upskills.projeto.objects.Room.RoomElement;
import pt.upskills.projeto.objects.Room.Water;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

public abstract class Enemy implements ImageTile {

    protected Position positionEnemy;
    private int life;
    private int damage;
    protected int range;
    private int scoreValue;
    private static int scoreCountEnemy;

    public Enemy(int damage, int life, int range, int scoreValue, Position position) {
        this.damage = damage;
        this.life = life;
        this.range = range;
        this.scoreValue = scoreValue;
        this.positionEnemy = position;
    }

    public Position getPosition() {
        return positionEnemy;
    }

    public void setPositionEnemy(Position pos) {
        positionEnemy = pos;
    }

    public static void restartEnemy(){scoreCountEnemy=0;}

    public int distance() {
        int distance;
        distance = (int) Math.sqrt((Math.pow((getPosition().getX() - Engine.hero.getHeroPosition().getX()), 2) + Math.pow((getPosition().getY() - Engine.hero.getHeroPosition().getY()), 2)));
        return distance;
    }


    public void addScoreCountEnemy() {
        scoreCountEnemy += scoreValue;
    }

    public static int getScoreCountEnemy() {
        return scoreCountEnemy;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getLife() {
        return life;
    }

    public void sufferDamage(int damage) {
        life -= damage;
        died();
    }

    /*public void dropLoot(int dropRate) {
        int random = (int) (Math.random() * dropRate);
        switch (random) {
            case 0:
                FireballPotion fireballPotion = new FireballPotion(getPosition());
                Engine.addNewImage(fireballPotion);
                break;
            case 1:
                LifePotion potion = new LifePotion(getPosition());
                Engine.addNewImage(potion);
                break;
            case 2:
                Sword sword = new Sword(getPosition());
                Engine.addNewImage(sword);
                break;
            case 3:
                GoodMeat goodMeat = new GoodMeat(getPosition());
                Engine.addNewImage(goodMeat);
                break;
        }
    }*/

    public boolean isDead() {
        if (life <= 0) return true;
        else return false;
    }

    public void died() {
        if (isDead()) {
            addScoreCountEnemy();
            for (ImageTile tile : Engine.tiles)
                if (tile.getPosition() == getPosition()) {
                    Engine.tiles.remove(tile);
                    Engine.gui.removeImage(tile);
                    Engine.gui.update();
                    return;
                }
        }
    }

    Position newPosition = null;

    public void enemyAttack(Position newAttackPosition) {
        for (ImageTile a : Engine.tiles) {
            if (a instanceof Hero) {
                if (((Hero) a).getLife() > 0) {
                    if (a.getPosition().equals(newAttackPosition)) {
                        ((Hero) a).sufferDamage(damage);
                        return;
                    }
                }
            }
        }
    }

    public boolean validEnemyMove(Position newPositionToValidate) {
        for (ImageTile a : Engine.tiles) {
            if (a instanceof RoomElement || a instanceof Door || a instanceof Enemy || a instanceof Hero || a instanceof Barricade) {
                if (a.getPosition().equals(newPositionToValidate)) {
                    if (a instanceof Barricade) {
                        if (((Barricade) a).getBarricadeType().equals("burnt")) {
                            return true;
                        }
                        if(((Barricade) a).getBarricadeType().equals("built")){
                            return false;
                        }
                    }
                }
                if (a.getPosition().equals(newPositionToValidate)) {
                    return false;
                }

            }
        }
        return true;
    }

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

    public void moveEnemy() {
        int random;
        random = (int) (Math.random() * 4);
        Position newPosition = null;
        if (distance() > range) {
            switch (random) {
                case 0:
                    newPosition = positionEnemy.plus(Direction.UP.asVector());
                    break;
                case 1:
                    newPosition = positionEnemy.plus(Direction.DOWN.asVector());
                    break;
                case 2:
                    newPosition = positionEnemy.plus(Direction.LEFT.asVector());
                    break;
                case 3:
                    newPosition = positionEnemy.plus(Direction.RIGHT.asVector());
                    break;
            }
            if (validEnemyMove(newPosition)) {
                positionEnemy = newPosition;
            }
        }
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+","+getLife()+";";
    }
}