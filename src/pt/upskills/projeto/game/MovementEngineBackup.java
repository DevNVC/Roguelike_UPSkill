package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Enemy;

public class MovementEngineBackup implements Runnable {


    @Override
    public void run() {

        while (true) {
            try {
                for (ImageTile a : Engine.tiles) {
                    if (a instanceof Enemy) {
                        ((Enemy) a).moveEnemy();
                        ((Enemy) a).enemyBecomesAgressive();
                        System.out.println("thread is running");
                    }
                }
            } catch (IllegalAccessError | ThreadDeath e) {
                e.printStackTrace();

            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();}
    }}
    
}

