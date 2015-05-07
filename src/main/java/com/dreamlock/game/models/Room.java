package com.dreamlock.game.models;

import com.dreamlock.game.jsonParser.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A Model of a room
 */
public class Room {
    private String description;
    private String title;
    private HashMap<String, Room> exits;  // stores the exits of this room.
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
}
