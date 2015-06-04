package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Availability;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;

public class CommandUtils {
    IGameContext gameContext;
    List <Item> inventoryItems;
    List <Item> roomItems;
    List <Door> roomDoors;


    CommandUtils(IGameContext gameContext) {
        this.gameContext = gameContext;

        this.inventoryItems = new ArrayList<>();
        this.inventoryItems.addAll(gameContext.getPlayer().getInventory().getItems());
        this.roomItems = new ArrayList<>();
        this.roomItems.addAll(gameContext.getCurrentRoom().getItems());
        this.roomDoors = new ArrayList<>();
        this.roomDoors.addAll(gameContext.getCurrentRoom().getDoors());

        List<Item> containerItems = new ArrayList<>();
        for (Item item : this.roomItems) {
            if ((item.getType().equals(ItemType.CONTAINER))) {
                Container container = (Container) item;

                if (!(boolean) container.getStats().get(Stats.LOCKED)) {
                    containerItems.addAll(container.getItems());
                }
            }
        }
        this.roomItems.addAll(containerItems);
    }

    public Availability checkItemAvailability(Word word, List<Item> items) {
        boolean exists = false;
        int duplicates = 0;

        for (Item item : items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                exists = true;
                duplicates++;
            }
        }
        if (exists){
            if (duplicates>1){
                return Availability.DUPLICATE;
            }
            else {
                return Availability.UNIQUE;
            }
        }
        return Availability.NON_EXISTENT;
    }

    public Availability checkDoorAvailability(Word word, List<Door> doors) {
        boolean exists = false;
        int duplicates = 0;

        for (Door door  : doors) {
            if (door.getName().toLowerCase().contains(word.getDescription()) ||
                    door.getDescription().toLowerCase().contains(word.getDescription())) {
                exists = true;
                duplicates++;
            }
        }
        if (exists){
            if (duplicates>1){
                return Availability.DUPLICATE;
            }
            else {
                return Availability.UNIQUE;
            }
        }
        return Availability.NON_EXISTENT;
    }

    //FOR PLAYER ITEMS
    public Item getInventoryItem(Word word) {
        for (Item item : inventoryItems) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    //FOR ROOM ITEMS
    public Item getRoomItem(Word word) {
        for (Item item : roomItems) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    public Item getItem(Word word) {
        List<Item> items = new ArrayList<>();
        items.addAll(inventoryItems);
        items.addAll(roomItems);

        for (Item item : items) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    //FOR ROOM DOORS
    public Door getRoomDoor(Word word) {
        for (Door door: roomDoors) {
            if (door.getName().toLowerCase().contains(word.getDescription()) || door.getDescription().toLowerCase().contains(word.getDescription())) {
                return door;
            }
        }
        return null;
    }

    public List<Item> getInventoryItems() {
        return inventoryItems;
    }

}
