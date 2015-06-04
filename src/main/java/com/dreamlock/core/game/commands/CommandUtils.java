package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.*;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.Player;
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


    CommandUtils (IGameContext gameContext) {
        this.gameContext = gameContext;
        this.inventoryItems = gameContext.getPlayer().getInventory().getItems();
        this.roomItems = gameContext.getCurrentRoom().getItems();
        this.roomDoors = gameContext.getCurrentRoom().getDoors();

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

    public ItemAvailability checkItemAvailability (Word word, List<Item> items) {
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
                return ItemAvailability.DUPLICATE;
            }
            else {
                return ItemAvailability.UNIQUE;
            }
        }
        return ItemAvailability.NON_EXISTENT;
    }

    public DoorAvailability checkDoorAvailability (Word word, List<Door> doors) {
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
                return DoorAvailability.DUPLICATE;
            }
            else {
                return DoorAvailability.UNIQUE;
            }
        }
        return DoorAvailability.NON_EXISTENT;
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
    public Item getRoomItem (Word word) {
        for (Item item : roomItems) {
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                return item;
            }
        }
        return null;
    }

    //FOR ROOM DOORS
    public Door getRoomDoor (Word word) {
        for (Door door: roomDoors) {
            if (door.getName().toLowerCase().contains(word.getDescription()) ||
                    door.getDescription().toLowerCase().contains(word.getDescription())) {
                return door;
            }
        }
        return null;
    }

    public List<Item> getInventoryItems() {
        return inventoryItems;
    }

    public boolean isEmptySlot(EquipmentSlot equipmentSlot){
        Item item = gameContext.getPlayer().getSlot(equipmentSlot);
        if (item == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
