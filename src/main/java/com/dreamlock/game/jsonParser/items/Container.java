package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ContainerDTO;
import com.dreamlock.game.models.Word;
import com.dreamlock.game.states.itemStates.CanNotPickUp;
import com.dreamlock.game.states.itemStates.CanOpen;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class Container extends Item{

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void removeItems() {
        items.clear();
    }

    public Item getSpecificItem(Word word) {
        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    public Container(){

    }

    public Container(String jsonItem, List<Item> items) {
        this.items = items;

        Gson gson = new Gson();
        ContainerDTO containerDTO= gson.fromJson(jsonItem, ContainerDTO.class);
        id = containerDTO.getId();
        type = containerDTO.getType();
        name = containerDTO.getName();
        description = containerDTO.getDescription();

        stats = new HashMap<>();
        stats.put(Stats.LOCKED, containerDTO.isLocked());
        stats.put(Stats.MATCH, containerDTO.getMatch());

        states = new HashMap<>();
        // Item states
        states.put(ActionState.PICK_UP, new CanNotPickUp());
        states.put(ActionState.OPEN, new CanOpen());
    }
}
