package com.dreamlock.parsers.jsonParser.items;

import com.dreamlock.parsers.jsonParser.DTOs.itemDTOs.MiscellaneousDTO;
import com.google.gson.Gson;

import java.util.HashMap;

public class Miscellaneous extends Item {
    public Miscellaneous(String jsonItem) {
        Gson gson = new Gson();
        MiscellaneousDTO miscellaneousDTO = gson.fromJson(jsonItem, MiscellaneousDTO.class);
        id = miscellaneousDTO.getId();
        type = miscellaneousDTO.getType();
        name = miscellaneousDTO.getName();
        description = miscellaneousDTO.getDescription();

        properties = new HashMap<>();
        properties.put("pickable", miscellaneousDTO.isPickable());
    }
}
