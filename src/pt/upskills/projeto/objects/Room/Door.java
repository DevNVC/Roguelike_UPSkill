package pt.upskills.projeto.objects.Room;

import pt.upskills.projeto.game.Engine;
import pt.upskills.projeto.gui.ImageTile;
import pt.upskills.projeto.rogue.utils.Position;

public class Door implements ImageTile {

    private Position position;
    private String typeOfDoor;
    private int doorNumber;
    private Integer roomNumber;
    private Integer nextRoomDoorNumber;

    public Door(String typeOfDoor, int doorNumber, int roomNumber, int nextRoomDoorNumber) {
        this.doorNumber = doorNumber;
        this.typeOfDoor = typeOfDoor;
        this.roomNumber = roomNumber;
        this.nextRoomDoorNumber=nextRoomDoorNumber;
    }

    public void setPosition(Position doorPosition) {
        this.position = doorPosition;
    }

    public Integer getNextRoomDoorNumber() {
        return nextRoomDoorNumber;
    }

    public void setTypeOfDoor(String typeOfDoor) {
        this.typeOfDoor = typeOfDoor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getDoorNumber() {
        return doorNumber;
    }

    public String getTypeOfDoor() {
        return typeOfDoor;
    }


    @Override
    public String getName() {
        if (typeOfDoor.equals("D")) {
            return "DoorClosed";
        }
        if (typeOfDoor.equals("E")) {
            return "DoorWay";
        }
        if (typeOfDoor.equals("L")) {
            return "DoorLocked";
        }
        if (typeOfDoor.equals("O")) {
            return "DoorOpen";
        }
        if (typeOfDoor.equals("<")) {
            return "StairsUp";
        }
        if (typeOfDoor.equals(">")) {
            return "StairsDown";
        }
        if (typeOfDoor.equals("Z")) {
            return "StairsDownCaves";
        }
        if (typeOfDoor.equals("Q")) {
            return "StairsUpCaves";
        }
        if (typeOfDoor.equals("a")) {
            return "DoorSpecial";
        }
        if (typeOfDoor.equals("I")) {
            return "GateLocked";
        }
        return null;
    }

    @Override
    public Position getPosition() {
        return position;
    }


    @Override
    public String toString() {
        return "Door"+","+getPosition().getX()+","+getPosition().getY()+","+getTypeOfDoor()+","+getDoorNumber()+","+getRoomNumber()+","+nextRoomDoorNumber+";";
    }
}
