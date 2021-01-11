package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.*;
import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.GameOver.GameOver;
import pt.upskills.projeto.objects.Items.*;
import pt.upskills.projeto.objects.Room.*;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;
import pt.upskills.projeto.rogue.utils.Vector2D;

import javax.swing.event.ListDataEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Hero implements ImageTile, Observer {

    private Position position;
    private int life;
    private final int maxLife = 8;
    private int damage = 0;
    private Direction lastDirection;
    private Direction oppositeLastDirection;
    private int numberOfMovements;
    private int numberFireBalls;
    private String heroFacing = "right";


    public Hero(Position position) {
        this.position = position;
        life = 1;
    }

    public int getNumberFireBalls() {
        return numberFireBalls;
    }

    public void setNumberFireBalls(int fireballsNumber) {
        numberFireBalls = fireballsNumber;
    }

    public boolean isDead() {
        if (getLife() <= 0) {
            setHeroFacing("dead");
            GameOver.gameOver();
            return true;
        }
        return false;
    }

    public void setHeroFacing(String facing) {
        heroFacing = facing;
        Engine.gui.update();
    }


    public void saveScore() {
        Score test = new Score("ignore");
        test.saveScore();
    }

    public void restart(Object arg) {
        if (arg.equals(KeyEvent.VK_R)) {
            setHeroFacing("right");
            setNumberFireBalls(0);
            Fireball.resetFireball();
            setLife(1);
            Enemy.restartEnemy();
            Status.status.clear();
            Status.startStatusBar();
            Sword ignore = new Sword(new Position(0, 0));
            ignore.resetItems();
            numberOfMovements = 0;
            Room.resetRooms();
            Engine.restart();

        }
    }

    public void restartTwo() {
            setHeroFacing("right");
            setNumberFireBalls(0);
            Fireball.resetFireball();
            setLife(1);
            Enemy.restartEnemy();
            Status.status.clear();
            Status.startStatusBar();
            Sword ignore = new Sword(new Position(0, 0));
            ignore.resetItems();
            numberOfMovements = 0;
            Room.resetRooms();
            Engine.restart();
    }

    public void sufferDamage(int damage) {
        life -= damage;
        Status.updateLifeBar();
        isDead();
    }

    public void setLife(int newlife) {
        life = newlife;
    }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public int getNumberOfMovements() {
        return this.numberOfMovements;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public Direction getLastDirection() {
        return this.lastDirection;
    }

    public Direction getOppositeLastDirection() {
        return this.oppositeLastDirection;
    }

    public int getLife() {
        return life;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int extraDamage) {
        this.damage = +extraDamage;
    }


    @Override
    public String getName() {
        if (heroFacing.equals("right"))
            return "Hero";
        if (heroFacing.equals("left"))
            return "HeroLeft";
        if (heroFacing.equals("dead"))
            return "HeroDead";
        else {
            return null;
        }
    }

    public Position getHeroPosition() {
        return this.position;
    }

    Position newPosition = getHeroPosition();

    public Boolean canWalk(ImageTile a) {
        if (a instanceof Wall ||a instanceof WallPrison || a instanceof WallPrisonDecorated ||a instanceof WallSewers || a instanceof WallDecorated || a instanceof WallLessDecorated || a instanceof Barricade || a instanceof WaterDark) {
            if (a.getPosition().equals(newPosition)) {
                if (a instanceof Barricade) {
                    if (((Barricade) a).getBarricadeType().equals("burnt")) {
                        return true;
                    }
                }
            }
            if (a.getPosition().equals(newPosition)) {
                return false;
            }
        }
        return true;
    }

    public Boolean isDoor(ImageTile a) {
        if (a instanceof Door) {
            if (a.getPosition().equals(newPosition)) {
                if(((Door) a).getTypeOfDoor().equals("I")){
                    saveScore();
                    restartTwo();
                }
                if (((Door) a).getTypeOfDoor().equals("L")) {
                    for(ImageTile tile:Item.items)
                    if (tile instanceof Key) {
                        if (((Key) tile).canUnlock()){
                        ((Key) tile).unlock();
                        ((Door) a).setTypeOfDoor("O");
                        Engine.gui.update();
                        break;
                        }
                    }
                    return false;
                }
                int nextDoor = ((Door) a).getNextRoomDoorNumber();
                if (Room.roomThatWasVisited(((Door) a).getRoomNumber(), nextDoor)) {
                    return false;
                } else {
                    Room.nextRoom(((Door) a).getRoomNumber());
                    return false;
                }

            }

        }
        return true;
    }

    public Boolean isEnemy(ImageTile a) {
        if (a instanceof Enemy) {
            if (a.getPosition().equals(newPosition)) {
                if (getDamage() > 0) {
                    ((Enemy) a).sufferDamage(getDamage());
                }
                return false;
            }
        }
        return true;
    }

    public Boolean isPot(ImageTile tile) {
        if (tile instanceof Pot) {
            if (tile.getPosition().equals(newPosition)) {
                ((Pot) tile).drinkFromThePot();
                return false;
            }
        }
        return true;
    }

    public void isItem() {
        for (ImageTile a : Engine.tiles) {
            if (a instanceof Item) {
                if (a instanceof Food) {
                    if (a.getPosition().equals(newPosition)) {
                        ((Food) a).eat(a);
                        break;
                    }
                } else if (a instanceof Potion) {
                    if (a.getPosition().equals(newPosition)) {
                        ((Potion) a).drink(a);
                        break;
                    }
                } else if (a.getPosition().equals(newPosition)) {
                    ((Item) a).checkIfAddItem(a);
                    ((Item) a).addWeaponDamage(a);
                    Engine.gui.update();
                    break;
                }
            }
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }


    /**
     * This method is called whenever the observed object is changed. This function is called when an
     * interaction with the graphic component occurs {{@link ImageMatrixGUI}}
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Integer keyCode = (Integer) arg;
        try {
            if (isDead()) {
                Engine.hero.saveScore();
                restart(arg);
                return;
            }
            if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                if (getPosition().getY() <= 8) {
                    newPosition = position.plus(Direction.DOWN.asVector());
                    lastDirection = Direction.DOWN;
                    oppositeLastDirection = Direction.UP;
                }
            }
            if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                if (getPosition().getY() > 0) {
                    newPosition = position.plus(Direction.UP.asVector());
                    lastDirection = Direction.UP;
                    oppositeLastDirection = Direction.DOWN;
                }
            }
            if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                if (getPosition().getX() >= 0) {
                    newPosition = position.plus(Direction.LEFT.asVector());
                    lastDirection = Direction.LEFT;
                    oppositeLastDirection = Direction.RIGHT;
                    setHeroFacing("left");
                }
            }
            if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                if (getPosition().getX() <= 10) {
                    newPosition = position.plus(Direction.RIGHT.asVector());
                    lastDirection = Direction.RIGHT;
                    oppositeLastDirection = Direction.LEFT;
                    setHeroFacing("right");
                }

            }
            if (keyCode == KeyEvent.VK_SPACE) {
                if (getNumberFireBalls() > 0) {
                    if (Fireball.validateIfCanCastDueToWall()) {
                        Fireball fireball = new Fireball(position);
                        FireBallThread fireBallThread = new FireBallThread(lastDirection, fireball);
                        fireBallThread.start();
                        fireball.useFireball();
                        Engine.gui.update();
                        Engine.addNewImage(fireball);
                    }
                } else {
                    return;
                }
            }
            if (keyCode == KeyEvent.VK_1) {
                for (ImageTile item : Item.items) {
                    if (item instanceof Item) {
                        if (((Item) item).validateCanDropItem()) {
                            if (item.getPosition().getX() == 7) {
                                ((Item) item).dropItem(item);
                                ((Item) item).setPosition(getPosition().plus(getLastDirection().asVector()));
                                if (item instanceof Weapon) {
                                    ((Weapon) item).removeWeaponDamage(item);
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (keyCode == KeyEvent.VK_2) {
                for (ImageTile item : Item.items) {
                    if (item instanceof Item) {
                        if (((Item) item).validateCanDropItem()) {
                            if (item.getPosition().getX() == 8) {
                                ((Item) item).dropItem(item);
                                ((Item) item).setPosition(getPosition().plus(getLastDirection().asVector()));
                                if (item instanceof Weapon) {
                                    ((Weapon) item).removeWeaponDamage(item);
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (keyCode == KeyEvent.VK_3) {
                for (ImageTile item : Item.items) {
                    if (item instanceof Item) {
                        if (((Item) item).validateCanDropItem()) {
                            if (item.getPosition().getX() == 9) {
                                ((Item) item).dropItem(item);
                                ((Item) item).setPosition(getPosition().plus(getLastDirection().asVector()));
                                if (item instanceof Weapon) {
                                    ((Weapon) item).removeWeaponDamage(item);
                                }
                                break;
                            }
                        }
                    }
                }
            }
            for (ImageTile a : Engine.tiles) {
                if (!canWalk(a)) {
                    return;
                }
                if (!isDoor(a)) {
                    return;
                }
                if (!isEnemy(a)) {
                    return;
                }
                if (!isPot(a)) {
                    return;
                }
            }
            isItem();
            position = newPosition;
        } catch (NullPointerException e) {
            return;
        }
        numberOfMovements++;
    }

    @Override
    public String toString() {
        return "Hero" + "," + getPosition().getX() + "," + getPosition().getY() + "," + getLife() + "," + getDamage() + "," +
                getNumberOfMovements() + "," + getNumberFireBalls() + ";";
    }
}
