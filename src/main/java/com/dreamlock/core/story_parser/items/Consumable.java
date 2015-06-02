package com.dreamlock.core.story_parser.items;

import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.states.itemStates.*;
import com.dreamlock.core.story_parser.DTOs.itemDTOs.ConsumableDTO;
import com.google.gson.Gson;

import java.util.HashMap;

public class Consumable extends Item {
    private String effect;
    private String state;

    public Consumable(String jsonItem) {
        Gson gson = new Gson();
        ConsumableDTO consumableDTO = gson.fromJson(jsonItem, ConsumableDTO.class);
        id = consumableDTO.getId();
        type = ItemType.valueOf(consumableDTO.getType().toUpperCase());
        state = consumableDTO.getState();
        name = consumableDTO.getName();
        description = consumableDTO.getDescription();
        effect = consumableDTO.getEffect();

        // Set Stats
        stats = new HashMap<>();
        stats.put(Stats.HEALTH, consumableDTO.getHealthStat());
        stats.put(Stats.STAMINA, consumableDTO.getStaminaStat());
        stats.put(Stats.STRENGTH, consumableDTO.getStrengthStat());
        stats.put(Stats.AGILITY, consumableDTO.getAgilityStat());
        states = new HashMap<>();
        // Item states
        states.put(ActionState.PICK_UP, new CanPickUp());
        states.put(ActionState.DROP, new CanDrop());
        states.put(ActionState.EQUIP, new CanNotEquip());
        states.put(ActionState.USE, new CanNotUse());
        states.put(ActionState.OPEN, new CanNotOpen());

        if (state.equals("Drink")) {
            states.put(ActionState.EAT, new CanNotEat());
            states.put(ActionState.DRINK, new CanDrink());
        }
        if (state.equals("Food")) {
            states.put(ActionState.EAT, new CanEat());
            states.put(ActionState.DRINK, new CanNotDrink());
        }
    }

    public String getState() {
        return state;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
