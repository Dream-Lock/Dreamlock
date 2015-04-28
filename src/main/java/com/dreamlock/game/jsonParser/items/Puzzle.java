package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.PuzzleDTO;
import com.dreamlock.game.states.*;
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

        states = new ArrayList<>();
        // changing states
        states.add(new CanPickUp());
        states.add(new CanNotDrop());
        // if picked up states
        states.add(new CanNotEquip());
        states.add(new CanUse());
        states.add(new CanNotOpen());
    }
}
