package pt.upskills.projeto.objects.Items;

import pt.upskills.projeto.rogue.utils.Position;

public class FireballPotion extends Potion {


    public FireballPotion(Position position) {
        super(1,5, position);
    }

    @Override
    public String getName() {
        return "FireballPotion";
    }


}
