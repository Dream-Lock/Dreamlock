package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Open implements ICommand{
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
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
                        outputMessages.addAll(openItem(gameContext, objectName));
                        return outputMessages;
                    }
                    outputMessages.add(new OutputMessage(1125));
                    return outputMessages;
                }
                else {
                    outputMessages.add(new OutputMessage(2001));
                    return outputMessages;
                }
            }

            if (doorExists) {               // Checking for doors
                int doorDuplicates = gameContext.getCurrentRoom().hasDoorDuplicates(words.get(2));

                if (doorDuplicates == 1) {

                    if (invItemExists) {
                        outputMessages.addAll(openDoor(gameContext, objectName));
                        return outputMessages;
                    }
                    else {

                        outputMessages.add(new OutputMessage(1125)); //item doesn't exist in inv.
                        return outputMessages;
                    }
                }
                else {
                    outputMessages.add(new OutputMessage(2002)); // duplicates exist
                    return outputMessages;
                }
            }

        }
        else {          // SIMPLE OPEN COMMAND USED
            if (itemExists) {
                int itemDuplicates = gameContext.getCurrentRoom().hasItemDuplicates(words.get(2));
                int itemCount = gameContext.getPlayer().getInventory().getItemCount(words.get(2));
                if (itemDuplicates == 1 || itemCount > 1) {
                    outputMessages.addAll(openItem(gameContext, objectName));
                    return outputMessages;
                }
                else {
                    outputMessages.add(new OutputMessage(2001));
                    return outputMessages;
                }
            }
            if (doorExists) {
                int doorDuplicates = gameContext.getCurrentRoom().hasDoorDuplicates(words.get(2));
                if (doorDuplicates == 1) {
                    outputMessages.addAll(justOpenDoor(gameContext, objectName));
                    return outputMessages;
                }
                else {
                    outputMessages.add(new OutputMessage(2002));
                    return outputMessages;
                }
            }
        }
        outputMessages.add(new OutputMessage(1062));
        return outputMessages;
    }

    private List<OutputMessage> openItem (IGameContext gameContext, Word itemName) {
        List <OutputMessage> outputMessages =  new ArrayList<>();
        List <OutputMessage> contOutput = new ArrayList<>();

        Item tempItem = gameContext.getCurrentRoom().getSpecificItem(itemName);
        outputMessages.add(new OutputMessage(tempItem.getId()));   // item to print
        outputMessages.add(tempItem.doAction(ActionState.OPEN, gameContext));
        if (tempItem.getType().equals(ItemType.CONTAINER)) {
            Container containerItem = (Container) tempItem;
            if (!(boolean) containerItem.getStats().get(Stats.LOCKED)){
                contOutput.add(new OutputMessage(1124));

                for (Item item : containerItem.getItems()) {
                    contOutput.add(new OutputMessage(item.getId()));
                }
                outputMessages.addAll(contOutput);
            }
        }
        return outputMessages;
    }

    private List<OutputMessage> openDoor (IGameContext gameContext, Word doorName) {
        List <OutputMessage> outputMessages = new ArrayList<>();
        Door door = gameContext.getCurrentRoom().getSpecificDoor(doorName);

        if (!door.isLocked()) {
            outputMessages.add(new OutputMessage(door.getId()));
            outputMessages.add(new OutputMessage(1123)); // door is already opened
            return outputMessages;
        }
        else if (gameContext.getPlayer().hasKey(door.getRequiredKey())) {
            door.unlock();
            outputMessages.add(new OutputMessage(door.getId()));
            outputMessages.add(new OutputMessage(1120)); //successful unlock
            return outputMessages;
        }

        outputMessages.add(new OutputMessage(1126)); //wrong key
        return outputMessages;
    }

    private List<OutputMessage> justOpenDoor (IGameContext gameContext, Word doorName) {
        List <OutputMessage> outputMessages = new ArrayList<>();
        Door door = gameContext.getCurrentRoom().getSpecificDoor(doorName);

        if (!door.isLocked()) {
            outputMessages.add(new OutputMessage(door.getId()));
            outputMessages.add(new OutputMessage(1123)); // door is already opened
            return outputMessages;
        }
        outputMessages.add(new OutputMessage(door.getId()));
        outputMessages.add(new OutputMessage(1122)); //successful unlock
        return outputMessages;
    }
}
