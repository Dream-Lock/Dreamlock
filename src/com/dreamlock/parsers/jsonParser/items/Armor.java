package com.dreamlock.parsers.jsonParser.items;

import com.dreamlock.parsers.jsonParser.DTOs.itemDTOs.ArmorDTO;
import com.google.gson.Gson;

import java.util.HashMap;

public class Armor extends Item {
    private String defence;

    public Armor(String jsonItem) {
        Gson gson = new Gson();
        ArmorDTO armorDTO = gson.fromJson(jsonItem, ArmorDTO.class);
        id = armorDTO.getId();
        type = armorDTO.getType();
        name = armorDTO.getName();
        description = armorDTO.getDescription();
        defence = armorDTO.getDefence();

        properties = new HashMap<>();
        properties.put("pickable", armorDTO.isPickable());
        properties.put("defence", armorDTO.getDefence());
    }

    public String getDefence() {
        return defence;
    }

    public void setDefence(String defence) {
        this.defence = defence;
    }
}
