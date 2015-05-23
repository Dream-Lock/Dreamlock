package com.dreamlock.game.models;

import com.dreamlock.game.combat.Combatant;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.EquipmentSlot;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.items.Armor;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.jsonParser.items.Weapon;
import com.dreamlock.game.states.combatStates.CanAttackState;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Player extends Combatant implements Serializable {
    private Inventory inventory;

    public Player(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
        this.setStamina(1);
        this.setStrength(1);
        this.setAgility(1);
        this.setHealth(10 + getStamina());
        this.setAttack(1 + getStrength());
        this.setDefense(1 + getAgility());

        states = new HashMap<>();
        states.put(ActionState.ATTACK, new CanAttackState());


        chest = null;
        head = null;
        main_hand = null;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Item getSlot(EquipmentSlot slot){
        switch(slot){
            case HEAD:
                if(head != null)
                    return head.getValue();
                else
                    return null;
            case CHEST:
                if(chest != null)
                    return chest.getValue();
                else
                    return null;
            case MAIN_HAND:
                if(main_hand != null)
                    return main_hand.getValue();
                else
                    return null;
            default:
                return null;
        }
    }

    public void equipItem(Item item) {
        switch (item.getEquipmentSlot()) {
            case HEAD:
                head = new ImmutablePair<>(item.getName(), (Armor) item);
                break;
            case CHEST:
                chest = new ImmutablePair<>(item.getName(), (Armor)  item);
                break;
            case MAIN_HAND:
                main_hand = new ImmutablePair<>(item.getName(),(Weapon)  item);
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
            case MAIN_HAND:
                main_hand = null;
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
}
