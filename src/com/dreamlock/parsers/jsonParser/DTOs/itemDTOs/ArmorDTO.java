package com.dreamlock.parsers.jsonParser.DTOs.itemDTOs;

public class ArmorDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private boolean pickable;
    private String defence;

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

    public String getDefence() {
        return defence;
    }

    public boolean isPickable() {
        return pickable;
    }
}
