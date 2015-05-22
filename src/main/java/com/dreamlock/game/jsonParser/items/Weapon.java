package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.DTOs.itemDTOs.WeaponDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.HashMap;

public class Weapon extends Item {

    public Weapon(String jsonItem) {
        Gson gson = new Gson();
        WeaponDTO weaponDTO = gson.fromJson(jsonItem, WeaponDTO.class);
        id = weaponDTO.getId();
        type = weaponDTO.getType();
        name = weaponDTO.getName();
        description = weaponDTO.getDescription();

        equipmentSlot = weaponDTO.getEquipmentSlot();

        stats = new HashMap<>();
        stats.put(Stats.ATTACK, weaponDTO.getAttack());
        stats.put(Stats.STAMINA, weaponDTO.getStamina());
        stats.put(Stats.STRENGTH, weaponDTO.getStrength());
        stats.put(Stats.AGILITY, weaponDTO.getAgility());

        states = new HashMap<>();
        // changing states
        states.put(ActionState.PICK_UP, new CanPickUp());
        states.put(ActionState.DROP, new CanDrop());
        states.put(ActionState.EQUIP, new CanEquip());
        states.put(ActionState.UNEQUIP, new CanUnequip());
        states.put(ActionState.USE, new CanNotUse());
        states.put(ActionState.OPEN, new CanNotOpen());
    }
}