package com.dreamlock.game.jsonParser.DTOs;

import java.util.ArrayList;

public class RoomDTO {
    private int id;
    private String path;
    private ArrayList<ItemDTO> items;
    private ArrayList<ExitsDTO> exits;

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }

    public ArrayList<ExitsDTO> getExits() {
        return exits;
    }

}
