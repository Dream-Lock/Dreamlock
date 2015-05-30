package com.dreamlock.core.story_parser.DTOs.itemDTOs;

import com.dreamlock.core.game.constants.EquipmentSlot;

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

    public EquipmentSlot getEquipmentSlot() {
        if(equipmentSlot.equalsIgnoreCase("head"))
            return EquipmentSlot.HEAD;
        if(equipmentSlot.equalsIgnoreCase("chest"))
            return EquipmentSlot.CHEST;
        if(equipmentSlot.equalsIgnoreCase("hands"))
            return EquipmentSlot.HANDS;
        if(equipmentSlot.equalsIgnoreCase("legs"))
            return EquipmentSlot.LEGS;
        if(equipmentSlot.equalsIgnoreCase("feet"))
            return EquipmentSlot.FEET;
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
