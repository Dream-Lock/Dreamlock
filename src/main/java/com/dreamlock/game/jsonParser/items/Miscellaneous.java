package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.MiscellaneousDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.ArrayList;
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

        states = new ArrayList<>();
        // changing states
        states.add(new CanPickUp());
        states.add(new CanNotDrop());
        // if picked up states
        states.add(new CanNotEquip());
        states.add(new CanNotUse());
        states.add(new CanNotOpen());
    }
}
