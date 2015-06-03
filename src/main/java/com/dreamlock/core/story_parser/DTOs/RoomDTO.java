package com.dreamlock.core.story_parser.DTOs;

import java.util.ArrayList;

public class RoomDTO {
    private int id;
    private String path;
    private String description;
    private ArrayList<ItemDTO> items;
    private ArrayList<ExitsDTO> exits;
    private ArrayList<EnemyDTO> enemies;
    private ArrayList<DoorDTO> doors;

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }

    public ArrayList<ExitsDTO> getExits() {
        return exits;
    }

    public ArrayList<EnemyDTO> getEnemies(){
        return enemies;
    }

    public ArrayList<DoorDTO> getDoors() {
        return doors;
    }
}
