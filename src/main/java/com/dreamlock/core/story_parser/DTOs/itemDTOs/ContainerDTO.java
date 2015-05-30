package com.dreamlock.core.story_parser.DTOs.itemDTOs;

import com.dreamlock.core.story_parser.DTOs.ItemDTO;

import java.util.ArrayList;

public class ContainerDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private boolean locked;
    private int match;
    private ArrayList<ItemDTO> items;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getDescription() {
        return description;
    }

    public int getMatch() {
        return match;
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }
}
