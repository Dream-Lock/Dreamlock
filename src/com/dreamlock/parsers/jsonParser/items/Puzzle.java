package com.dreamlock.parsers.jsonParser.items;

import com.dreamlock.parsers.jsonParser.DTOs.itemDTOs.PuzzleDTO;
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

        properties = new HashMap<>();
        properties.put("pickable", puzzleDTO.isPickable());
        properties.put("droppable", puzzleDTO.isDroppable());
    }
}
