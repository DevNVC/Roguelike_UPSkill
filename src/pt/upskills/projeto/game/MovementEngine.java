package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Damage;
import pt.upskills.projeto.objects.Enemy;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MovementEngine implements Runnable {


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
                e.printStackTrace();
                Thread enemyMotor2 = new Thread(new MovementEngineBackup());
                enemyMotor2.start();
            }
        }

    }
}

