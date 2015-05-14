package com.dreamlock.game.jsonParser.DTOs.itemDTOs;

import com.dreamlock.game.jsonParser.items.Item;

public class ArmorDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private String defense;
    private Item.Slot slot;

    public Item.Slot getSlot() {
        return slot;
    }

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

    public String getDefense() {
        return defense;
    }

}
