package com.dreamlock.parsers.jsonParser.DTOs.itemDTOs;

public class RoomObjectDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private boolean pickable;
    private boolean movable;

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

    public boolean isMovable() {
        return movable;
    }

    public boolean isPickable() {
        return pickable;
    }
}
