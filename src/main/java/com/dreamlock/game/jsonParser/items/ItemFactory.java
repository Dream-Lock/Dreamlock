package com.dreamlock.game.jsonParser.items;

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
}
