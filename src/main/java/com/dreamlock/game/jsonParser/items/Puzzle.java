package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.jsonParser.DTOs.itemDTOs.PuzzleDTO;
import com.dreamlock.game.states.itemStates.*;
import com.google.gson.Gson;

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
        // Item states
        states.put(ActionState.PICK_UP, new CanPickUp());
        states.put(ActionState.DROP, new CanNotDrop());
        states.put(ActionState.EQUIP, new CanNotEquip());
        states.put(ActionState.USE, new CanUse());
        states.put(ActionState.OPEN, new CanNotOpen());
    }
}
