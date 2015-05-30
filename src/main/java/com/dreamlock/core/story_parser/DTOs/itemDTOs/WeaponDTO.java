package com.dreamlock.core.story_parser.DTOs.itemDTOs;

import com.dreamlock.core.game.constants.EquipmentSlot;

public class WeaponDTO {
    private int id;
    private String type;
    private String name;
    private String description;
    private String attack;
    private String stamina;
    private String strength;
    private String agility;
    private String equipmentSlot;

    public EquipmentSlot getEquipmentSlot() {
        if(equipmentSlot.equalsIgnoreCase("main_hand"))
            return EquipmentSlot.MAIN_HAND;
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

    public String getAttack() {
        return attack;
    }

    public String getStrength() {
        return strength;
    }

    public String getAgility() {
        return agility;
    }

    public String getStamina() {
        return stamina;
    }
}
