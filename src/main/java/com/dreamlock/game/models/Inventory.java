package com.dreamlock.game.models;

import com.dreamlock.game.jsonParser.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public List<Item> containsItems(Word word) {
        List<Item> foundItems = new ArrayList<>();

        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    public boolean containsItem(Word word) {
        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return true;
            }
        }
        return false;
    }

    public Item getSpecificItem(Word word) {
        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    public int hasDuplicates(Word word) {
        int count = 0;
        for (Item item : this.items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                count++;
            }
        }
        return count;
    }

    public int getItemCount(Word word) {
        int count = 0;
        for ( Item item : this.items) {
            if(item.getName().toLowerCase().equals(word.getDescription())) {
                count++;
            }
        }
        return count;
    }

}
