package com.dreamlock.parsers.jsonParser.items;

import com.dreamlock.parsers.jsonParser.DTOs.itemDTOs.ConsumableDTO;
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

        properties = new HashMap<>();
        properties.put("pickable", consumableDTO.isPickable());
    }
}
