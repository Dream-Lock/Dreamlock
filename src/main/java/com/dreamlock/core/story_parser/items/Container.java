package com.dreamlock.core.story_parser.items;

import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.game.states.itemStates.CanNotPickUp;
import com.dreamlock.core.game.states.itemStates.CanOpen;
import com.dreamlock.core.story_parser.DTOs.itemDTOs.ContainerDTO;
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

    public void removeItem (Item item) {
        items.remove(item);
    }

    public Container(){

    }

    public Container(String jsonItem, List<Item> items) {
        this.items = items;

        Gson gson = new Gson();
        ContainerDTO containerDTO= gson.fromJson(jsonItem, ContainerDTO.class);
        id = containerDTO.getId();
        type = ItemType.valueOf(containerDTO.getType().toUpperCase());
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
