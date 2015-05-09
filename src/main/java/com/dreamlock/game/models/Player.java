package com.dreamlock.game.models;

import com.dreamlock.game.combat.Combatant;
import com.dreamlock.game.states.combatStates.CanAttackState;

import java.io.Serializable;
import java.util.HashMap;

public class Player extends Combatant implements Serializable {
    private Inventory inventory;

    public Player(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
        this.setHealth(25);
        this.setAttack(5);
        this.setDefense(1);

        states = new HashMap<>();
        states.put("Attack", new CanAttackState());
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
