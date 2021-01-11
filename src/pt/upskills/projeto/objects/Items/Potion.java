package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Fireball;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Potion extends Item{
    private int magicGain;

    public Potion(int magicGain,int scoreValue, Position position) {
        super(scoreValue,position);
        this.magicGain=magicGain;
    }

    public void drink(ImageTile potion){
        if(Engine.hero.getNumberFireBalls()<3){
        Fireball.addFireball();
        Engine.removeImage(potion);
        addScoreCountItem();
        System.out.println("Just drank this");
        }

    }

    public int getMagicGain(){return magicGain;}

}
