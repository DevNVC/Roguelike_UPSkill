package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.objects.Fireball;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Position;

public class FireballPot extends Pot {
    private int magicGain=3;

    public FireballPot(Position position){
        super(position); }

    @Override
    public void drinkFromThePot(){
        if(Engine.hero.getNumberFireBalls()<3) {
            Fireball.addFireball();
            Fireball.addFireball();
            Fireball.addFireball();
            Engine.gui.update();
            System.out.println("Just drank from the FireballPot");
        }
    }

    @Override
    public String getName() {
        return "FireballPot";
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+";";
    }
}
