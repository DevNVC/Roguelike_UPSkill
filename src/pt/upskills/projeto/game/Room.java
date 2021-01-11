package pt.upskills.projeto.game;

import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.objects.Hero;
import pt.upskills.projeto.objects.Room.Door;
import pt.upskills.projeto.objects.Room.Floor;
import pt.upskills.projeto.rogue.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Room {


    public static HashMap<Integer, List<ImageTile>> roomsThatHaveBeenSaved = new HashMap<>();
    public static int currentRoom;

    public static void saveLastRoom(List<ImageTile> savingRoom) {
        List<ImageTile> copy = new ArrayList<>();
        copy.addAll(savingRoom);
        roomsThatHaveBeenSaved.put(currentRoom, copy);
    }


    public static boolean roomThatWasVisited(int roomNumberCheck, int nextDoor) {
        if (roomsThatHaveBeenSaved.containsKey(roomNumberCheck)) {
            if (roomsThatHaveBeenSaved.containsKey(currentRoom)) {
                roomsThatHaveBeenSaved.put(currentRoom, roomsThatHaveBeenSaved.get(currentRoom));
                for (ImageTile tile:roomsThatHaveBeenSaved.get(currentRoom)){
                    if(tile instanceof Door){
                    System.out.println(tile);}
                }
            } else {
                saveLastRoom(Engine.tiles);
                for (ImageTile tile:Engine.tiles){
                    if(tile instanceof Door){
                        System.out.println(tile);}
                }
            }
            Engine.gui.clearImages();
            Engine.gui.newImages(roomsThatHaveBeenSaved.get(roomNumberCheck));
            Engine.tiles = roomsThatHaveBeenSaved.get(roomNumberCheck);
            for (ImageTile tile:roomsThatHaveBeenSaved.get(roomNumberCheck)){
                if(tile instanceof Door){
                    System.out.println(tile);}
            }
            Position spawn=null;
            for(ImageTile tile:Engine.tiles){
                if(tile instanceof Door){
                    if(((Door) tile).getDoorNumber()==nextDoor){
                        spawn=tile.getPosition();
                    }
                }
            }
            Engine.tiles.remove(Engine.hero);
            Engine.hero.setPosition(spawn);
            Engine.tiles.add(Engine.hero);
            Engine.gui.update();
            currentRoom=roomNumberCheck;
            return true;
        }
        return false;
    }

    public static void nextRoom(int roomCheck) {
        saveLastRoom(Engine.tiles);
        Engine.tiles.clear();
        Engine.gui.clearImages();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Engine.tiles.add(new Floor(new Position(i, j)));
            }
        }
        Engine.readMapFile(Engine.tiles, roomCheck);
        Engine.gui.newImages(Engine.tiles);
        Engine.gui.update();
        currentRoom=roomCheck;
        Engine.doorList.clear();
    }

    public static void resetRooms(){
        roomsThatHaveBeenSaved.clear();
        currentRoom=0;

    }

}
