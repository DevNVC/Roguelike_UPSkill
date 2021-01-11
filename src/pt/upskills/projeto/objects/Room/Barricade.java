package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Barricade implements ImageTile {
    private Position position;

    private String barricadeType = "built";

    public Barricade(Position position){
        this.position=position;
    }
    public Barricade(String grassStatus, Position position){this.barricadeType =grassStatus;this.position=position;}

    public String getBarricadeType(){return barricadeType;}

    public void setBarricadeType(String typeOfBarricade) {
        this.barricadeType = typeOfBarricade;
    }

    @Override
    public String getName() {
        if(barricadeType.equals("burnt"))
            return "BurntGrass";
        else{
            return "Barricade";}
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+";";
    }
}
