package com.dreamlock.game.jsonParser.DTOs.itemDTOs;

public class PuzzleDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private boolean pickable;
    private boolean droppable;

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

    public boolean isDroppable() { return droppable;}
}
