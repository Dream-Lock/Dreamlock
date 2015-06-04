package com.dreamlock.core.game.models;

import com.dreamlock.core.game.combat.Combatant;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.states.combatStates.CanAttackState;
import com.dreamlock.core.story_parser.items.Armor;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.story_parser.items.Weapon;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player extends Combatant implements Serializable {
    private Inventory inventory;
    private int id;
    public Player(String name, Inventory inventory) {
        this.name = name;
        this.id = 9999;
        this.inventory = inventory;
        calculateStats();

        states = new HashMap<>();
        states.put(ActionState.ATTACK, new CanAttackState());

        chest = null;
        head = null;
        hands = null;
        legs = null;
        feet = null;
        main_hand = null;
        off_hand = null;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public void equipItem(Item item) {
        switch (item.getEquipmentSlot()) {
            case HEAD:
                head = new ImmutablePair<>(item.getName(), (Armor) item);
                break;
            case CHEST:
                chest = new ImmutablePair<>(item.getName(), (Armor)  item);
                break;
            case HANDS:
                hands = new ImmutablePair<>(item.getName(), (Armor)  item);
                break;
            case LEGS:
                legs = new ImmutablePair<>(item.getName(), (Armor)  item);
                break;
            case FEET:
                feet = new ImmutablePair<>(item.getName(), (Armor)  item);
                break;
            case MAIN_HAND:
                main_hand = new ImmutablePair<>(item.getName(),(Weapon)  item);
                break;
            case OFF_HAND:
                off_hand = new ImmutablePair<>(item.getName(),(Weapon)  item);
                break;
        }
    }

    public void initializeSlot(EquipmentSlot slot) {
        switch(slot){
            case HEAD:
                head = null;
                break;
            case CHEST:
                chest = null;
                break;
            case HANDS:
                hands = null;
                break;
            case LEGS:
                legs = null;
                break;
            case FEET:
                feet = null;
                break;
            case MAIN_HAND:
                main_hand = null;
                break;
            case OFF_HAND:
                off_hand = null;
                break;
        }
    }

    public boolean hasKey (int keyId) {
        for (Item item : inventory.getItems()) {
            if (item.getId() == keyId) {
                return true;
            }
        }
        return false;
    }

    public void addStats(Map<Stats, Object> stats) {
        stamina += (int) stats.get(Stats.STAMINA);
        calculateStats();
        health += (int) stats.get(Stats.HEALTH);
        if (health > maxHealth) {
            health = maxHealth;
        }

        strength += (int) stats.get(Stats.STRENGTH);
        agility += (int) stats.get(Stats.AGILITY);
        calculateStats();
    }

    public int getId() {
        return id;
    }

}
