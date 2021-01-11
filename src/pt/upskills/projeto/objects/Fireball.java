package pt.upskills.projeto.objects;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.FireTile;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Room.Barricade;
import pt.upskills.projeto.objects.Room.Grass;
import pt.upskills.projeto.objects.Room.RoomElement;
import pt.upskills.projeto.objects.Room.Water;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Direction;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.HashMap;

public class Fireball implements FireTile {


    private Position position;
    private int damage=3;
    private static boolean position0 = false;
    private static boolean position1 = false;
    private static boolean position2 = false;
    private static Position zero = new Position(0, 0);
    private static Position one = new Position(1, 0);
    private static Position two = new Position(2, 0);
    private static HashMap<Integer,ImageTile> fireballs=new HashMap<>();

    public Fireball(Position position) {
        this.position = position;
    }

    public int getDamage() {
        return damage;
    }

    public static void resetFireball(){
        fireballs.clear();
        position0=false;
        position1=false;
        position2=false;
    }

    public static void addFireball() {
        if(!position0){
        Engine.hero.setNumberFireBalls(Engine.hero.getNumberFireBalls()+1);
        fireballs.put(0,new Fireball(zero));
        Engine.addNewStatusImage(new Fireball(zero));
        position0=true;
        }else
        if(!position1){
            Engine.hero.setNumberFireBalls(Engine.hero.getNumberFireBalls()+1);
            fireballs.put(1,new Fireball(one));
            Engine.addNewStatusImage(new Fireball(one));
            position1=true;
        }else
        if(!position2){
            Engine.hero.setNumberFireBalls(Engine.hero.getNumberFireBalls()+1);
            Engine.addNewStatusImage(new Fireball(two));
            fireballs.put(2,new Fireball(two));
            position2=true;
        }
    }

    public void useFireball() {
        for(ImageTile fireball: Status.status){
            if (fireball instanceof Fireball) {
                if (fireballs.containsKey(2)) {
                    if(fireball.getPosition()==two){
                    position2 =false;
                    Engine.removeStatusImage(fireball);
                    fireballs.remove(2);
                    Engine.hero.setNumberFireBalls(Engine.hero.getNumberFireBalls()-1);
                    System.out.println(Engine.hero.getNumberFireBalls());
                    break;}
                }else
                    if(fireballs.containsKey(1)){
                    if(fireball.getPosition()==one){
                    position1 =false;
                    Engine.removeStatusImage(fireball);
                    fireballs.remove(1);
                    Engine.hero.setNumberFireBalls(Engine.hero.getNumberFireBalls()-1);
                    System.out.println(Engine.hero.getNumberFireBalls());
                    break;}
                }else
                if (fireballs.containsKey(0)) {
                    if(fireball.getPosition()==zero){
                    position0 =false;
                    Engine.removeStatusImage(fireball);
                    fireballs.remove(0);
                    Engine.hero.setNumberFireBalls(Engine.hero.getNumberFireBalls()-1);
                    System.out.println(Engine.hero.getNumberFireBalls());
                    break;}
                }

        }
    }
    }



    @Override
    public boolean validateImpact() {
        final Direction last = Engine.hero.getLastDirection();
        final Direction lastOpposite = Engine.hero.getOppositeLastDirection();
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof RoomElement) {
                if (tile.getPosition().equals(this.position)) {
                    Engine.removeImage(this);
                    return false;
                }
            }
            if (tile instanceof Enemy) {
                if (tile.getPosition().equals(getPosition())) {
                    ((Enemy) tile).sufferDamage(damage);
                    System.out.println("O inimigo sofre " + getDamage() + " pontos de dano.");
                    Engine.removeImage(this);
                    return false;
                }
            }
            if (tile instanceof Water) {
                if (tile.getPosition().equals(getPosition())) {
                    ((Water) tile).evaporate(tile);
                    break;
                }
            }
            if(tile instanceof Barricade){
                if (tile.getPosition().equals(getPosition())) {
                    ((Barricade) tile).setBarricadeType("burnt");
                    Engine.removeImage(this);
                    return false;
                }
            }
            if (tile instanceof Grass) {
                if (tile.getPosition().equals(getPosition())) {
                    ((Grass) tile).setGrassType("burnt");
                    Engine.gui.update();
                    return true;
                }
            }
        }
        //validar se a Fireball atingiu um objeto
        return true;
    }

    public static boolean validateIfCanCastDueToWall() {
        if (Engine.hero.getNumberOfMovements() == 0) {
            return false;
        }
        for (ImageTile tile : Engine.tiles) {
            if (tile instanceof RoomElement) {
                if (tile.getPosition().equals(Engine.hero.getHeroPosition().plus(Engine.hero.getLastDirection().asVector()))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;

    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+";";
    }
}
