package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.RoomObjectDTO;
import com.dreamlock.game.states.*;
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

        states = new ArrayList<>();
        // changing states
        states.add(new CanNotPickUp());
        states.add(new CanNotDrop());
        // static states
        states.add(new CanNotEquip());
        states.add(new CanNotUse());
        states.add(new CanOpen());
    }
}
