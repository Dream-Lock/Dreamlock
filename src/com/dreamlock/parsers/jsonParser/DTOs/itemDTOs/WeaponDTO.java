package com.dreamlock.parsers.jsonParser.DTOs.itemDTOs;

public class WeaponDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private boolean pickable;
    private String attack;

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

    public String getAttack() {
        return attack;
    }

    public boolean isPickable() {
        return pickable;
    }
}
