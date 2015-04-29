package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ArmorDTO;
import com.dreamlock.game.jsonParser.DTOs.itemDTOs.ContainerDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class Container extends Item{

    private List<Item> items;

    public List<Item> getItems() {
        return items;
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

        states = new HashMap<>();
        // changing states
        states.put("Pick Up", new CanNotPickUp());
        states.put("Drop", new CanNotDrop());
        // if picked up states
        states.put("Equip", new CanNotEquip());
        states.put("Use", new CanNotUse());
        states.put("Open", new CanOpen());
    }
}
