package com.dreamlock.parsers.jsonParser.items;

import com.dreamlock.parsers.jsonParser.DTOs.itemDTOs.RoomObjectDTO;
import com.google.gson.Gson;

import java.util.HashMap;

public class RoomObject extends Item {
    private boolean movable;

    public RoomObject(String jsonItem) {
        Gson gson = new Gson();
        RoomObjectDTO roomObjectDTO = gson.fromJson(jsonItem, RoomObjectDTO.class);
        id = roomObjectDTO.getId();
        type = roomObjectDTO.getType();
        name = roomObjectDTO.getName();
        description = roomObjectDTO.getDescription();
        movable = roomObjectDTO.isMovable();

        properties = new HashMap<>();
        properties.put("pickable", roomObjectDTO.isPickable());
        properties.put("movable", roomObjectDTO.isMovable());
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }
}
