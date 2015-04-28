package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.WeaponDTO;
import com.dreamlock.game.states.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class Weapon extends Item {

    public Weapon(String jsonItem) {
        Gson gson = new Gson();
        WeaponDTO weaponDTO = gson.fromJson(jsonItem, WeaponDTO.class);
        id = weaponDTO.getId();
        type = weaponDTO.getType();
        name = weaponDTO.getName();
        description = weaponDTO.getDescription();

        stats = new HashMap<>();
        stats.put("attack", weaponDTO.getAttack());

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
