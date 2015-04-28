package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ConsumableDTO;
import com.dreamlock.game.states.*;
import com.google.gson.Gson;

import java.util.ArrayList;
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

        states = new ArrayList<>();
        // changing states
        states.add(new CanPickUp());
        states.add(new CanNotDrop());
        // if picked up states
        states.add(new CanNotEquip());
        states.add(new CanUse());
        states.add(new CanNotOpen());
    }
}
