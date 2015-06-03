package com.dreamlock.core.game.models;

import com.dreamlock.core.story_parser.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventory implements Serializable {
    private List<Item> items;
    private int size;

    public Inventory(int size) {
        this.size = size;
        this.items = new ArrayList<Item>();
    }

    public Inventory(List<Item> items, int size) {
        this.items = items;
        this.size = size;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }
}
