package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.RoomObjectDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomObject extends Item {
    public RoomObject(String jsonItem) {
        Gson gson = new Gson();
        RoomObjectDTO roomObjectDTO = gson.fromJson(jsonItem, RoomObjectDTO.class);
        id = roomObjectDTO.getId();
        type = roomObjectDTO.getType();
        name = roomObjectDTO.getName();
        description = roomObjectDTO.getDescription();

        stats = new HashMap<>();

        states = new HashMap<>();
        // changing states
        states.put("Pick Up", new CanNotPickUp());
        states.put("Drop", new CanNotDrop());
        // static states
        states.put("Equip", new CanNotEquip());
        states.put("Use", new CanNotUse());
        states.put("Open", new CanOpen());
    }
}
