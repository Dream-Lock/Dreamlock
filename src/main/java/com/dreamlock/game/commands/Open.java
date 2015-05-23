package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Door;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Open implements ICommand{
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();
        Word objectName = words.get(2);
        boolean itemExists = gameContext.getCurrentRoom().containsItem(objectName);
        boolean doorExists = gameContext.getCurrentRoom().containsDoor(objectName);

        if (words.size() > 2) {

            Word invItemName = words.get(4);
            boolean invItemExists = gameContext.getPlayer().getInventory().containsItem(invItemName);

            if (itemExists) {               //Checking For items
                int itemDuplicates = gameContext.getCurrentRoom().hasItemDuplicates(words.get(2));
                int itemCount = gameContext.getPlayer().getInventory().getItemCount(words.get(2));

                if ( itemDuplicates == 1 || itemCount > 1 ) {       //check for duplicates

                    if (invItemExists) {            //key exists
                        output.addAll(openItem(gameContext, objectName));
                        return output;
                    }
                    output.add(10000);
                    output.add(1125);
                    return output;
                }
                else {
                    output.add(10000);
                    output.add(2001);
                    return output;
                }
            }

            if (doorExists) {               // Checking for doors
                int doorDuplicates = gameContext.getCurrentRoom().hasDoorDuplicates(words.get(2));

                if (doorDuplicates == 1) {

                    if (invItemExists) {
                        output.addAll(openDoor(gameContext, objectName));
                        return output;
                    }
                    else {
                        output.add(10000);
                        output.add(1125); //item doesn't exist in inv.
                        return output;
                    }
                }
                else {
                    output.add(10000);
                    output.add(2002); // duplicates exist
                    return output;
                }
            }

        }
        else {          // SIMPLE OPEN COMMAND USED
            if (itemExists) {
                int itemDuplicates = gameContext.getCurrentRoom().hasItemDuplicates(words.get(2));
                int itemCount = gameContext.getPlayer().getInventory().getItemCount(words.get(2));
                if (itemDuplicates == 1 || itemCount > 1) {
                    output.addAll(openItem(gameContext, objectName));
                    return output;
                }
                else {
                    output.add(10000);
                    output.add(2001);
                    return output;
                }
            }
            if (doorExists) {
                int doorDuplicates = gameContext.getCurrentRoom().hasDoorDuplicates(words.get(2));
                if (doorDuplicates == 1) {
                    output.addAll(justOpenDoor(gameContext, objectName));
                    return output;
                }
                else {
                    output.add(10000);
                    output.add(2002);
                    return output;
                }
            }
        }
        output.add(1062);
        return output;
    }

    private List<Integer> openItem (IGameContext gameContext, Word itemName) {
        List <Integer> output =  new ArrayList<>();
        List <Integer> contOutput = new ArrayList<>();

        Item tempItem = gameContext.getCurrentRoom().getSpecificItem(itemName);
        output.add(10000);
        output.add(tempItem.getId());   // item to print
        output.add(tempItem.doAction(ActionState.OPEN, gameContext));
        if (tempItem.getType().equals("Container")) {
            Container containerItem = (Container) tempItem;

            contOutput.add(10002);
            contOutput.add(1124);

            for (Item item : containerItem.getItems()) {
                contOutput.add(10002);
                contOutput.add(item.getId());
            }
            output.addAll(contOutput);
        }
        return output;
    }

    private List<Integer> openDoor (IGameContext gameContext, Word doorName) {
        List <Integer> output = new ArrayList<>();
        Door door = gameContext.getCurrentRoom().getSpecificDoor(doorName);

        if (!door.isLocked()) {
            output.add(10000);
            output.add(door.getId());
            output.add(1123); // door is already opened
            return output;
        }
        else if (gameContext.getPlayer().hasKey(door.getRequiredKey())) {
            door.unlock();
            output.add(10000);
            output.add(door.getId());
            output.add(1120); //successful unlock
            return output;
        }

        output.add(10000);
        output.add(1126); //wrong key
        return output;
    }

    private List<Integer> justOpenDoor (IGameContext gameContext, Word doorName) {
        List <Integer> output = new ArrayList<>();
        Door door = gameContext.getCurrentRoom().getSpecificDoor(doorName);

        if (!door.isLocked()) {
            output.add(10000);
            output.add(door.getId());
            output.add(1123); // door is already opened
            return output;
        }
        output.add(10000);
        output.add(door.getId());
        output.add(1122); //successful unlock
        return output;

    }
}
