package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.rogue.utils.Position;

public abstract class Weapon extends Item{

    private int weaponDamage;

    public Weapon(int weaponDamage,int scoreValue, Position position) {
        super(scoreValue,position);
        this.weaponDamage=weaponDamage;
    }

    public int getWeaponDamage(){return weaponDamage;}


}
