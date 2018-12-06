package Main.GameObjects;

import Main.Room;

public class Item {
    private Room room;
    private String name;
    private String description;
    private int id;
    private int stackSize;

    public Item(Room room, int id, String name, String description) {
        this.room = room;
        this.id = id;
        this.name = name;
        this.description = description;
        this.stackSize = 1;
    }

    public Item(Room room, int id, int stackSize, String name, String description) {
        this.room = room;
        this.id = id;
        this.stackSize = stackSize;
        this.name = name;
        this.description = description;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }
}
