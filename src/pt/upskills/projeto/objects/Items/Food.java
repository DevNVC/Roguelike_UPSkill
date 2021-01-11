package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Food extends Item{
    private int lifeGain;

    public Food(int lifeGain,int scoreValue,Position position) {
        super(scoreValue,position);
        this.lifeGain=lifeGain;
    }

    public void eat(ImageTile food){
        for(ImageTile tile:Engine.tiles){
            if(tile instanceof Hero){
                if(((Hero) tile).getLife()<((Hero) tile).getMaxLife()){
                    ((Hero) tile).setLife(((Hero) tile).getLife()+lifeGain);
                    addScoreCountItem();
                    while (((Hero) tile).getLife()>((Hero) tile).getMaxLife()){
                        ((Hero) tile).setLife(((Hero) tile).getLife()-1);
                    }
                    Engine.removeImage(food);
                    Status.updateLifeBar();
                    break;
                }
            }
        }
    }

    public int getLifeGain(){return lifeGain;}

}
