package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ConsumableDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.HashMap;

public class Consumable extends Item {
    public Consumable(String jsonItem) {
        Gson gson = new Gson();
        ConsumableDTO consumableDTO = gson.fromJson(jsonItem, ConsumableDTO.class);
        id = consumableDTO.getId();
        type = consumableDTO.getType();
        name = consumableDTO.getName();
        description = consumableDTO.getDescription();

        stats = new HashMap<>();

        states = new HashMap<>();
        // Item states
        states.put("Pick Up", new CanPickUp());
        states.put("Drop", new CanDrop());
        states.put("Equip", new CanNotEquip());
        states.put("Use", new CanUse());
        states.put("Open", new CanNotOpen());
    }
}
