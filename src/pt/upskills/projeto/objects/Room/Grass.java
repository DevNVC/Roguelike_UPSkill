package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Grass implements ImageTile {
    private Position position;

    private String grassType= "green";

    public Grass(Position position){
        this.position=position;
    }
    public Grass(String grassStatus, Position position){this.grassType=grassStatus;this.position=position;}


    public void setGrassType(String typeOfGrass) {
        this.grassType = typeOfGrass;
    }

    @Override
    public String getName() {
        if(grassType.equals("burnt"))
            return "BurntGrass";
        else{
            return "Grass";}
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
