package com.dreamlock.game.jsonParser.DTOs.itemDTOs;

import com.dreamlock.game.jsonParser.items.Item;

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

    public Item.EquipmentSlot getEquipmentSlot() {
        if(equipmentSlot.equalsIgnoreCase("main_hand"))
            return Item.EquipmentSlot.MAIN_HAND;
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
