package com.dreamlock.game.jsonParser.DTOs.itemDTOs;

import com.dreamlock.game.jsonParser.items.Item;

public class ArmorDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private String defense;
    private String stamina;
    private String strength;
    private String agility;
    private String equipmentSlot;

    public Item.EquipmentSlot getEquipmentSlot() {
        if(equipmentSlot.equalsIgnoreCase("head"))
            return Item.EquipmentSlot.HEAD;
        if(equipmentSlot.equalsIgnoreCase("chest"))
            return Item.EquipmentSlot.CHEST;
        return null;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDefense() {
        return defense;
    }

    public String getAgility() {
        return agility;
    }

    public String getStrength() {
        return strength;
    }

    public String getStamina() {
        return stamina;
    }
}
