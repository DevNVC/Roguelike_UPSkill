package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Position;

public class LifePot extends Pot {
    private int lifeGain=7;

    public LifePot(Position position){
        super(position); }

    @Override
    public void drinkFromThePot(){
        for(ImageTile tile:Engine.tiles){
            if (tile instanceof Hero){
                if (((Hero) tile).getLife()<((Hero) tile).getMaxLife()){
                    ((Hero) tile).setLife(((Hero) tile).getLife()+lifeGain);
                    while (((Hero) tile).getLife()>((Hero) tile).getMaxLife()){
                        ((Hero) tile).setLife(((Hero) tile).getLife()-1);
                    }
                    Status.updateLifeBar();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "LifePot";
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
