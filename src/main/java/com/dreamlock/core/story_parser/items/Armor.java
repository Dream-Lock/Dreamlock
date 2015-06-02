package com.dreamlock.core.story_parser.items;

import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.states.itemStates.*;
import com.dreamlock.core.story_parser.DTOs.itemDTOs.ArmorDTO;
import com.google.gson.Gson;

import java.util.HashMap;

public class Armor extends Item {

    public Armor(String jsonItem) {
        Gson gson = new Gson();
        ArmorDTO armorDTO = gson.fromJson(jsonItem, ArmorDTO.class);
        id = armorDTO.getId();
        type = ItemType.valueOf(armorDTO.getType().toUpperCase());
        name = armorDTO.getName();
        description = armorDTO.getDescription();

        equipmentSlot =  armorDTO.getEquipmentSlot();

        stats = new HashMap<>();
        stats.put(Stats.DEFENSE, armorDTO.getDefense());
        stats.put(Stats.STAMINA, armorDTO.getStamina());
        stats.put(Stats.STRENGTH, armorDTO.getStrength());
        stats.put(Stats.AGILITY, armorDTO.getAgility());

        states = new HashMap<>();
        // Item states
        states.put(ActionState.PICK_UP, new CanPickUp());
        states.put(ActionState.DROP, new CanDrop());
        states.put(ActionState.EQUIP, new CanEquip());
        states.put(ActionState.UNEQUIP, new CanUnequip());
        states.put(ActionState.USE, new CanNotUse());
        states.put(ActionState.DRINK, new CanNotDrink());
        states.put(ActionState.EAT, new CanNotEat());
        states.put(ActionState.OPEN, new CanNotOpen());
    }
}