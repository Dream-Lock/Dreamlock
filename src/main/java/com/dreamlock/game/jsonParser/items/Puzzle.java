package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.PuzzleDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle extends Item {
    public Puzzle(String jsonItem) {
        Gson gson = new Gson();
        PuzzleDTO puzzleDTO = gson.fromJson(jsonItem, PuzzleDTO.class);
        id = puzzleDTO.getId();
        type = puzzleDTO.getType();
        name = puzzleDTO.getName();
        description = puzzleDTO.getDescription();

        stats = new HashMap<>();

        states = new HashMap<>();
        // changing states
        states.put("Pick Up", new CanPickUp());
        states.put("Drop", new CanNotDrop());
        // if picked up states
        states.put("Equip", new CanNotEquip());
        states.put("Use", new CanUse());
        states.put("Open", new CanNotOpen());
    }
}
