package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.jsonParser.DTOs.itemDTOs.RoomObjectDTO;
import com.dreamlock.game.states.itemStates.CanNotPickUp;
import com.dreamlock.game.states.itemStates.CanOpen;
import com.google.gson.Gson;

import java.util.HashMap;

public class RoomObject extends Item {
    public RoomObject(String jsonItem) {
        Gson gson = new Gson();
        RoomObjectDTO roomObjectDTO = gson.fromJson(jsonItem, RoomObjectDTO.class);
        id = roomObjectDTO.getId();
        type = roomObjectDTO.getType();
        name = roomObjectDTO.getName();
        description = roomObjectDTO.getDescription();

        stats = new HashMap<>();

        states = new HashMap<>();
        // changing states
        states.put(ActionState.PICK_UP, new CanNotPickUp());
        states.put(ActionState.OPEN, new CanOpen());
    }
}
