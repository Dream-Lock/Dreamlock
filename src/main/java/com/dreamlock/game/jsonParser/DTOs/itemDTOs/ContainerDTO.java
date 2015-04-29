package com.dreamlock.game.jsonParser.DTOs.itemDTOs;

import com.dreamlock.game.jsonParser.DTOs.ItemDTO;
import java.util.ArrayList;

public class ContainerDTO {
    private int id;
    private String type;
    private String name;
    private String description;
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

    public String getDescription() {
        return description;
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }
}
