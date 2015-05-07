package com.dreamlock.game.models;

import com.dreamlock.game.combat.Combatant;

public class Player extends Combatant{
    private String name;
    private Inventory inventory;

    public Player(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
        this.setHealth(25);
        this.setAttack(5);
        this.setDefense(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
