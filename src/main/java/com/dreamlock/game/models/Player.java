package com.dreamlock.game.models;

import java.io.Serializable;

public class Player implements Serializable{
    private String name;
    private Inventory inventory;

    public Player(String name, Inventory inventory) {
        this.name = name;
        this.inventory = inventory;
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
