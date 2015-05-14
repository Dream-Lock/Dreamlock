package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ArmorDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.HashMap;

public class Armor extends Item {


    private Slot slot;

    public Armor(String jsonItem) {
        Gson gson = new Gson();
        ArmorDTO armorDTO = gson.fromJson(jsonItem, ArmorDTO.class);
        id = armorDTO.getId();
        type = armorDTO.getType();
        name = armorDTO.getName();
        description = armorDTO.getDescription();

        slot =  armorDTO.getSlot();

        stats = new HashMap<>();
        stats.put("defense", armorDTO.getDefense());

        states = new HashMap<>();
        // Item states
        states.put("Pick Up", new CanPickUp());
        states.put("Drop", new CanDrop());
        states.put("Equip", new CanEquip());
        states.put("Use", new CanNotUse());
        states.put("Open", new CanNotOpen());
    }
}
