package pt.upskills.projeto.objects.status;


import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Enemy;
import pt.upskills.projeto.objects.Fireball;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.Items.Item;
import pt.upskills.projeto.objects.Room.Floor;
import pt.upskills.projeto.rogue.utils.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Status implements ImageTile {
    public static List<ImageTile> status;

    public Status(){status=new ArrayList<>();startStatusBar();}

    public static void startStatusBar(){
        for(int i=0; i<10; i++){                //adds tiles
                status.add(new Black(new Position(i, 0)));
            }
        updateLifeBar();

    }


    public static void updateLifeBar(){
        int currentLife= Engine.hero.getLife();
        int i;
        if(currentLife % 2 == 0) {
            for (i = 0; i < currentLife; i += 2) {
                status.add(new GreenLifeBar(new Position(i / 2 + 3, 0)));
            }
        } else {
            for (i = 0; i < currentLife-1; i += 2) {
                status.add(new GreenLifeBar(new Position(i / 2 + 3, 0)));
            }
            status.add(new RedGreenLifeBar(new Position(i/2+3,0)));
            i+=2;
        }

        for(; i < 8; i+=2) {
            status.add(new RedLifeBar(new Position(i/2+3,0)));
        }

        Engine.gui.clearStatus();
        Engine.gui.newStatusImages(status);
    }



    @Override
    public String getName() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public String toString() {
        return getName()+","+getPosition().getX()+","+getPosition().getY()+";";
    }
}
