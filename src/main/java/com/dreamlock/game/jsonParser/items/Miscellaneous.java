package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.MiscellaneousDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.HashMap;

public class Miscellaneous extends Item {
    public Miscellaneous(String jsonItem) {
        Gson gson = new Gson();
        MiscellaneousDTO miscellaneousDTO = gson.fromJson(jsonItem, MiscellaneousDTO.class);
        id = miscellaneousDTO.getId();
        type = miscellaneousDTO.getType();
        name = miscellaneousDTO.getName();
        description = miscellaneousDTO.getDescription();

        stats = new HashMap<>();

        states = new HashMap<>();
        // Item states
        states.put("Pick Up", new CanPickUp());
        states.put("Drop", new CanDrop());
        states.put("Equip", new CanNotEquip());
        states.put("Use", new CanNotUse());
        states.put("Open", new CanNotOpen());
    }
}
