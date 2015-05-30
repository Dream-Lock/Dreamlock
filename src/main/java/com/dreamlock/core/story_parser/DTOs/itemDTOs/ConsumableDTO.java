package com.dreamlock.core.story_parser.DTOs.itemDTOs;

public class ConsumableDTO {
    private int id;
    private String type;
    private String state;
    private String name;
    private String description;
    private String effect;
    private int healthStat;
    private int staminaStat;
    private int strengthStat;
    private int agilityStat;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEffect() {
        return effect;
    }

    public int getHealthStat() {
        return healthStat;
    }

    public int getStaminaStat() {
        return staminaStat;
    }

    public int getStrengthStat() {
        return strengthStat;
    }

    public int getAgilityStat() {
        return agilityStat;
    }
}
