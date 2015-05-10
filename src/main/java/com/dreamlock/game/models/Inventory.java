package com.dreamlock.game.models;

import com.dreamlock.game.jsonParser.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory implements Serializable{
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

    public Item containsItem(Map<Integer, Word> words) {
        Word word = words.get(2);

        for (Item item : this.items) {
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    public List<Item> containsItems(Map<Integer, Word> words) {
        Word word = words.get(2);
        List<Item> foundItems = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }
}
