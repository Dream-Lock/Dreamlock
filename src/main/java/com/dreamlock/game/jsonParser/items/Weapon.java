package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.WeaponDTO;
import com.dreamlock.game.states.itemStates.*;
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

        states = new HashMap<>();
        // changing states
        states.put("Pick Up", new CanPickUp());
        states.put("Drop", new CanNotDrop());
        // if picked up states
        states.put("Equip", new CanEquip());
        states.put("Use", new CanNotUse());
        states.put("Open", new CanNotOpen());
    }
}
