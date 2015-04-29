package com.dreamlock.game.jsonParser.items;

import java.util.List;

public class ItemFactory {
    public Item createItem(String type, String jsonItem) {
        Item item = null;

        try {
            switch (type){
                case "Weapon":
                    item = new Weapon(jsonItem);
                    break;
                case "Armor":
                    item = new Armor(jsonItem);
                    break;
                case "Puzzle":
                    item = new Puzzle(jsonItem);
                    break;
                case "Consumable":
                    item = new Consumable(jsonItem);
                    break;
                case "Misc":
                    item = new Miscellaneous(jsonItem);
                    break;
                case "RoomObject":
                    item = new RoomObject(jsonItem);
                    break;
                default:
                    throw new IllegalArgumentException("parsing error! plz check your items!");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return item;
    }
    public Item createItem(String jsonItem, List<Item> items) {        //Special Constructor Alternative for Containers
        Item item = new Container(jsonItem, items);
        return item;
    }

}
