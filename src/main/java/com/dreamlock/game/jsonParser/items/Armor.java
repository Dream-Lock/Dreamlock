package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ArmorDTO;
import com.dreamlock.game.states.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class Armor extends Item {
    public Armor(String jsonItem) {
        Gson gson = new Gson();
        ArmorDTO armorDTO = gson.fromJson(jsonItem, ArmorDTO.class);
        id = armorDTO.getId();
        type = armorDTO.getType();
        name = armorDTO.getName();
        description = armorDTO.getDescription();

        stats = new HashMap<>();
        stats.put("defence", armorDTO.getDefence());

        states = new ArrayList<>();
        // changing states
        states.add(new CanPickUp());
        states.add(new CanNotDrop());
        // if picked up states
        states.add(new CanEquip());
        states.add(new CanNotUse());
        states.add(new CanNotOpen());
    }
}
