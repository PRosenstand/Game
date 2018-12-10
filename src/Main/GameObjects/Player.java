package Main.GameObjects;

import Main.Room;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player {
    private Room room;
    private HashMap<Integer, Item> inventory = new HashMap<>();
    private int Health = 100;
    private boolean death = false;

    public Player(Room room) {
        for (int i = 0; i < 10; i++) {
            inventory.put(i, new Item(null, 0, "Air", "Empty Debug Item"));
        }
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HashMap<Integer, Item> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Integer, Item> inventory) {
        this.inventory = inventory;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    /**
     * This also kills the player when health is 0 or below.
     *
     * @param damage
     */
    public void damage(int damage) {
        Health -= damage;
        if (Health <= 0) {
            death = true;
        }
    }

    /**
     * Adds an item to the players inventory.
     *
     * @param item The Item that should be added.
     * @return If the Item could be added.
     */
    public boolean addItem(Item item) {
        AtomicBoolean addedItem = new AtomicBoolean(false);
        inventory.entrySet().stream().filter(integerItemEntry -> integerItemEntry.getValue().getId() == 0).findFirst().ifPresent(integerItemEntry -> {
            inventory.put(integerItemEntry.getKey(), item);
            addedItem.set(true);
            System.out.println("Added item.");
        });
        if (!addedItem.get()) {
            System.out.println("Could not add Inventory is full");
        }
        return addedItem.get();
    }

    public boolean hasItem(Item item) {
        AtomicBoolean containsItem = new AtomicBoolean(false);
        getInventory().forEach((integer, item1) -> {
            if (item.equals(item1)) {
                containsItem.set(true);
            }
        });
        return containsItem.get();
    }
}
