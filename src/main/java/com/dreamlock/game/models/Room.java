package com.dreamlock.game.models;

import com.dreamlock.game.jsonParser.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Model of a room
 */
public class Room implements Serializable{
    private String description;
    private String title;
    private HashMap<String, Room> exits;  // stores the exits of this room.
    private List<Item> items;
    private int id;
    private List<Enemy> enemies;
    private boolean locked;
    private int requires;
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getRequires() {
        return requires;
    }

    public void setRequires(int requires) {
        this.requires = requires;
    }

    public Item containsItem(Map<Integer, Word> words) {
        Word word = words.get(2);

        for (Item item : this.items) {
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    public List<Item> containsItems(Map<Integer, Word> words) {
        Word word = words.get(2);
        List<Item> foundItems = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }
}
