package com.dreamlock.game.jsonParser.DTOs.itemDTOs;

public class ConsumableDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private boolean pickable;

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

    public boolean isPickable() {
        return pickable;
    }
}
