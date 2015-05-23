package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ConsumableDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.HashMap;

public class Consumable extends Item {
    private String effect;

    public Consumable(String jsonItem) {
        Gson gson = new Gson();
        ConsumableDTO consumableDTO = gson.fromJson(jsonItem, ConsumableDTO.class);
        id = consumableDTO.getId();
        type = consumableDTO.getType();
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
        states.put(ActionState.USE, new CanUse());
        states.put(ActionState.OPEN, new CanNotOpen());
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
