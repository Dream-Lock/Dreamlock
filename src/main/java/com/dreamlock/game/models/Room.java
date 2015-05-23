package com.dreamlock.game.models;

import com.dreamlock.game.jsonParser.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Model of a room
 */
public class Room implements Serializable{
    private String description;
    private String title;
    private HashMap<String, Room> exits;  // stores the exits of this room.
    private List<Door> doors;  // stores the doors of this room.

    private List<Item> items;
    private int id;
    private List<Enemy> enemies;
    /**
     * Constructor for <b>empty rooms</b>.
     */
    public Room() {
        id = 0;
        title = "wall";
        description = "wall";
    }

    /**
     * Constructor for <b>rooms</b>.
     * @param title         Room name
     * @param description   Room description
     */
    public Room(String title, String description, int id) {
        this.description = description;
        this.title = title;
        this.exits = new HashMap<>();
        this.doors = new ArrayList();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }

    public void setExits(HashMap<String, Room> exits) {
        this.exits = exits;
    }

    public void setExit(String direction, Room room) {
        this.exits.put(direction, room);
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List <Door> doors) {
        this.doors = doors;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies){
        this.enemies = enemies;
    }

    public Item getSpecificItem(Word word) {
        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return item;
            }
        }
        return null;
    }
    public Door getSpecificDoor(Word word) {
        for (Door door: this.doors) {
            if (door.getName().toLowerCase().contains(word.getDescription()) ||
                    door.getDescription().toLowerCase().contains(word.getDescription())) {
                return door;
            }
        }
        return null;
    }
    public boolean containsItem(Word word) {
        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return true;
            }
        }
        return false;
    }
    public boolean containsDoor(Word word) {
        for (Door door : this.doors) {
            if (door.getName().toLowerCase().contains(word.getDescription()) ||
                    door.getDescription().toLowerCase().contains(word.getDescription())) {
                return true;
            }
        }
        return false;
    }
//
    public List<Item> containsItems(Word word) {
        List<Item> foundItems = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    public List<Door> containsDoors(Word word) {
        List<Door> foundDoors = new ArrayList<>();

        for (Door door : this.doors) {
            if (door.getName().toLowerCase().contains(word.getDescription())) {
                foundDoors.add(door);
            }
        }
        return foundDoors;
    }
    public int hasItemDuplicates (Word word) {
        int count = 0;
        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                count++;
            }
        }
        return count;
    }
    public int hasDoorDuplicates (Word word) {
        int count = 0;
        for (Door door : this.doors) {
            if (door.getName().toLowerCase().contains(word.getDescription()) ||
                    door.getDescription().toLowerCase().contains(word.getDescription())) {
                count++;
            }
        }
        return count;
    }

}
