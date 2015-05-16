package com.dreamlock.game.models;

import com.dreamlock.game.combat.Combatant;
import com.dreamlock.game.jsonParser.items.Armor;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.jsonParser.items.Weapon;
import com.dreamlock.game.states.combatStates.CanAttackState;

import java.io.Serializable;
import java.util.HashMap;

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
        states.put("Attack", new CanAttackState());

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

    public Item getSlot(Item.EquipmentSlot slot){
        switch(slot){
            case HEAD:
                return head;
            case CHEST:
                return chest;
            case MAIN_HAND:
                return main_hand;
            default:
                return null;
        }
    }

    public void equipItem(Item item) {
        switch (item.getEquipmentSlot()) {
            case HEAD:
                head = (Armor) item;
                break;
            case CHEST:
                chest = (Armor) item;
                break;
            case MAIN_HAND:
                main_hand = (Weapon) item;
                break;
        }
    }
}
