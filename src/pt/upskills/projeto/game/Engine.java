package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageMatrixGUI;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.*;
import pt.upskills.projeto.objects.Items.*;
import pt.upskills.projeto.objects.Room.*;
import pt.upskills.projeto.objects.status.Status;
import pt.upskills.projeto.rogue.utils.Position;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Engine {
    public static ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
    public static List<ImageTile> tiles = new ArrayList<>();
    public static Thread enemyMotor = new Thread(new MovementEngine());
    public static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    public static Hero hero = new Hero(new Position(0, 0));
    public static List<Door> doorList = new ArrayList<>();
    public static List<Score> scoreBoardlist=new ArrayList<>();

    public void init() {     //starts the game by loading all the main assets and calling methods to action
        for (int i = 0; i < 10; i++) {                //adds tiles
            for (int j = 0; j < 10; j++) {
                tiles.add(new Floor(new Position(i, j)));
            }
        }
        readMapFile(tiles, 0);               //inicia o primeiro nivel
        gui.addObserver(hero);                  //inicia o observer
        Status status = new Status();             //inicia a status bar
        gui.newStatusImages(status.status);     //sets statusBar
        gui.newImages(tiles);                   //sets tiles
        gui.go();                               //starts the gui
        //executor.scheduleWithFixedDelay(enemyMotor, 700, 700, TimeUnit.MILLISECONDS); //Starts the enemy movement thread
        enemyMotor.start();
        while (true) {                           //keeps the gui updating
        gui.update();
        }
    }

    public static void addNewImage(ImageTile image) {
        tiles.add(image);
        gui.addImage(image);
        gui.removeImage(hero);
        gui.addImage(hero);
        gui.update();
    }

    public static void removeImage(ImageTile image) {
        tiles.remove(image);
        gui.removeImage(image);
        gui.update();
    }

    public static void addNewStatusImage(ImageTile image) {
        Status.status.add(image);
        gui.addStatusImage(image);
        gui.update();
    }

    public static void removeStatusImage(ImageTile image) {
        Status.status.remove(image);
        gui.removeStatusImage(image);
        gui.update();
    }

    public static void readWriteScoreBoard() {
        try {
            Scanner scanScoreBoard = new Scanner(new File("files/Scoreboard.txt"));
            String firstLine = scanScoreBoard.nextLine();
            while (scanScoreBoard.hasNextLine()){
                String line = scanScoreBoard.nextLine();
                String[] splitLine = line.split(";");
                    int tempScore = parseInt(splitLine[0]);
                    long tempDate = parseLong(splitLine[1]);
                    Score score = new Score(tempScore, tempDate);
                    scoreBoardlist.add(score);
                }
            scanScoreBoard.close();
            System.out.println("SCOREBOARD LIDA");
            for (Score score : scoreBoardlist) {
                System.out.println(score);
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        try {
            PrintWriter printWriter = new PrintWriter(new File("files/Scoreboard.txt"));
            printWriter.println("TopScore;Date");
            Collections.sort(scoreBoardlist);
            for (Score score : scoreBoardlist) {
                printWriter.println(score);
            }
            printWriter.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    //lê objetos que podem ser pisados por personagens e lê a legenda das portas
    public static void readFloorStuff(int level) {
        try {
            Scanner scanMap = new Scanner(new File("rooms/room" + level + ".txt"));
            if(level==5){
                for (int i = 0; i < 10; i++) {                //adds tiles
                    for (int j = 0; j < 10; j++) {
                        tiles.add(new FloorSpecial(new Position(i, j)));
                    }
                }
            }
            if(level==7){
                for (int i = 0; i < 10; i++) {                //adds tiles
                    for (int j = 0; j < 10; j++) {
                        tiles.add(new FloorBoss(new Position(i, j)));
                    }
                }
            }

            int l = 0;
            while (scanMap.hasNextLine()) {
                String linha = scanMap.nextLine();
                String[] separar = linha.split("");
                for (int i = 0; i < separar.length; i++) {
                    Position pos = new Position(i, l);
                    if (separar[0].equals("#")) {
                        //separar2[] linha.split
                        try {
                            if (separar.length > 2) {
                                System.out.println(linha);
                                String typeOfDoor = separar[4];
                                Integer doorNumber = Integer.parseInt(separar[2]);
                                Integer roomNumber = Integer.parseInt(separar[10]);
                                Integer nextRoomDoorNumber = Integer.parseInt(separar[16]);
                                System.out.println("Sou a porta" + doorNumber + " e levo ao quarto " + roomNumber);
                                doorList.add(new Door(typeOfDoor, doorNumber, roomNumber, nextRoomDoorNumber));
                                //System.out.println(doorList);

                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("não consegui apanhar o numero certo");
                        }
                        l--;
                        break;
                    }
                    if (separar[i].equals("H")) tiles.add(new Hole(pos));
                    if (separar[i].equals("e")) tiles.add(new Grass(pos));
                    if (separar[i].equals("t")) tiles.add(new Trap(pos));
                    if (separar[i].equals("w")) tiles.add(new Water(pos));
                    if (separar[i].equals("d")) tiles.add(new WaterDark(pos));
                    if (separar[i].equals("M")) tiles.add(new FireballPot(pos));
                    if (separar[i].equals("P")) tiles.add(new LifePot(pos));
                    if (separar[i].equals("X")) tiles.add(new FloorSpecial(pos));
                    if (separar[i].equals("C")) tiles.add(new FloorBloody(pos));
                    if (separar[i].equals("V")) tiles.add(new FloorSewer(pos));
                }
                l++;
            }

        } catch (IllegalArgumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //cria lista de portas para leitura de mapa
    public static void findDoorNumber(int doorNumber, Position pos) {
        for (Door door : doorList) {
            if (door.getDoorNumber() == doorNumber) {
                door.setPosition(pos);
                tiles.add(door);
            }
        }
    }

    //verifica se uma String contem um numero (ate 10)
    public static boolean isNumber(String element) {
        if (element.contains("0") || element.contains("1") || element.contains("2") || element.contains("3") || element.contains("4") || element.contains("5") || element.contains("6") ||
                element.contains("7") || element.contains("8") || element.contains("9") || element.contains("10")) {
            return true;
        } else {
            return false;
        }
    }

    //le o mapa pela primeira vez
    public static void readMapFile(List<ImageTile> tile, int level) {
        readFloorStuff(level);
        try {
            Scanner scanMap = new Scanner(new File("rooms/room" + level + ".txt"));
            int j = 0;
            int l = 0;
            while (scanMap.hasNextLine()) {
                String linha = scanMap.nextLine();
                String[] separar = linha.split("");
                for (int i = 0; i < separar.length; i++) {
                    boolean isNumber = separar[i].contains("1") || separar[i].contains("2") || separar[i].contains("3") || separar[i].contains("4") || separar[i].contains("5") || separar[i].contains("6");
                    Position pos = new Position(i, j);
                    if (separar[0].equals("#")) {
                        j--;
                        break;
                    }
                    if (isNumber(separar[i])) {
                        int doorNumber = Integer.parseInt(separar[i]);
                        findDoorNumber(doorNumber, pos);
                    }

                    if (separar[i].equals("W")) tiles.add(new Wall(pos));
                    if (separar[i].equals("J")) tiles.add(new Barricade(pos));
                    if (separar[i].equals("c")) tiles.add(new WallSewers(pos));
                    if (separar[i].equals("Y")) tiles.add(new WallDecorated(pos));
                    if (separar[i].equals("y")) tiles.add(new WallLessDecorated(pos));
                    if (separar[i].equals("u")) tiles.add(new WallPrison(pos));
                    if (separar[i].equals("U")) tiles.add(new WallPrisonDecorated(pos));
                    if (separar[i].equals("R")) tiles.add(new Sky(pos));

                    if (separar[i].equals("h")) {
                        hero.setPosition(pos);
                        tiles.add(hero);
                    }
                    if (separar[i].equals("S")) {
                        tiles.add(new Skeleton(pos));
                    }
                    if (separar[i].equals("B")) {
                        tiles.add(new Bat(pos));
                    }
                    if (separar[i].equals("G")) {
                        tiles.add(new BadGuy(pos));
                    }
                    if (separar[i].equals("T")) {
                        tiles.add(new Thief(pos));
                    }
                    if(separar[i].equals("A")){
                        tiles.add(new Tengu(pos));
                    }
                    if (separar[i].equals("g")) tiles.add(new GoodMeat(pos));
                    if (separar[i].equals("k")) tiles.add(new Key(pos));
                    if (separar[i].equals("s")) tiles.add(new Sword(pos));
                    if (separar[i].equals("m")) tiles.add(new Hammer(pos));
                    if (separar[i].equals("f")) tiles.add(new Fireball(pos));
                    if (separar[i].equals("p")) tiles.add(new FireballPotion(pos));
                    if (separar[i].equals("n")) tiles.add(new LifePotion(pos));

                }
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //faz clear e recomeça o jogo
    public static void restart() {
        tiles.clear();
        scoreBoardlist.clear();
        for (Score score : scoreBoardlist) {
            System.out.println(score);
        }
        gui.clearImages();
        System.out.println("2");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles.add(new Floor(new Position(i, j)));
            }
        }
        System.out.println("3");
        readMapFile(tiles, 0);
        System.out.println("4");
        gui.newImages(tiles);
    }


    public static void main(String[] args) {
        Engine engine = new Engine();
        engine.init();
        scoreBoardlist.clear();
    }
}
