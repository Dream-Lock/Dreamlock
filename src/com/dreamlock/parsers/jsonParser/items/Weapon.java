package com.dreamlock.parsers.jsonParser.items;

import com.dreamlock.parsers.jsonParser.DTOs.itemDTOs.WeaponDTO;
import com.google.gson.Gson;

import java.util.HashMap;

public class Weapon extends Item {
    private String attack;

    public Weapon(String jsonItem) {
        Gson gson = new Gson();
        WeaponDTO weaponDTO = gson.fromJson(jsonItem, WeaponDTO.class);
        id = weaponDTO.getId();
        type = weaponDTO.getType();
        name = weaponDTO.getName();
        description = weaponDTO.getDescription();
        attack = weaponDTO.getAttack();

        properties = new HashMap<>();
        properties.put("pickable", weaponDTO.isPickable());
        properties.put("attack", weaponDTO.getAttack());
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }
}
