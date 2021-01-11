package pt.upskills.projeto.objects.GameOver;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.GameOver.Y1.*;
import pt.upskills.projeto.objects.GameOver.Y2.*;
import pt.upskills.projeto.objects.GameOver.Y3.*;
import pt.upskills.projeto.objects.GameOver.Y4.*;
import pt.upskills.projeto.objects.Hero;

import java.util.ArrayList;
import java.util.List;

public class GameOver {

    public static List<ImageTile> gameOverMessage= new ArrayList<>();


    public static void gameOver(){
    gameOverMessage.add(new GOy1x1());
    gameOverMessage.add(new GOy1x2());
    gameOverMessage.add(new GOy1x3());
    gameOverMessage.add(new GOy1x4());
    gameOverMessage.add(new GOy1x5());
    gameOverMessage.add(new GOy1x6());
    gameOverMessage.add(new GOy1x7());
    gameOverMessage.add(new GOy1x8());
    gameOverMessage.add(new GOy2x1());
    gameOverMessage.add(new GOy2x2());
    gameOverMessage.add(new GOy2x3());
    gameOverMessage.add(new GOy2x4());
    gameOverMessage.add(new GOy2x5());
    gameOverMessage.add(new GOy2x6());
    gameOverMessage.add(new GOy2x7());
    gameOverMessage.add(new GOy2x8());
    gameOverMessage.add(new GOy3x1());
    gameOverMessage.add(new GOy3x2());
    gameOverMessage.add(new GOy3x3());
    gameOverMessage.add(new GOy3x4());
    gameOverMessage.add(new GOy3x5());
    gameOverMessage.add(new GOy3x6());
    gameOverMessage.add(new GOy3x7());
    gameOverMessage.add(new GOy3x8());
    gameOverMessage.add(new GOy4x1());
    gameOverMessage.add(new GOy4x2());
    gameOverMessage.add(new GOy4x3());
    gameOverMessage.add(new GOy4x4());
    gameOverMessage.add(new GOy4x5());
    gameOverMessage.add(new GOy4x6());
    gameOverMessage.add(new GOy4x7());
    gameOverMessage.add(new GOy4x8());
    Engine.gui.newImages(gameOverMessage);
    Engine.gui.update();






    }
}
