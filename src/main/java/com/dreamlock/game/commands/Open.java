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
        List<Integer> contOutput = new ArrayList<>();

        boolean itemExists = gameContext.getCurrentRoom().containsItem(words.get(2));
        boolean doorExists = gameContext.getCurrentRoom().containsDoor(words.get(2));
        boolean invItemExists = gameContext.getPlayer().getInventory().containsItem(words.get(4));

        if (itemExists) {           //Checking for room items (ex. Containers)

            int itemDuplicates = gameContext.getCurrentRoom().hasItemDuplicates(words.get(2));

            if (itemDuplicates == 1 ) {
                if (invItemExists) {

                    Item tempItem = gameContext.getCurrentRoom().getSpecificItem(words.get(2));
                    if (tempItem.getType().equals("Container")) {
                        output.add(10000);
                        Container containerItem = (Container) tempItem;
                        output.add(containerItem.getId());   // item to print

                        contOutput.add(10002);
                        contOutput.add(1124);

                        for (Item item : containerItem.getItems()) {
                            contOutput.add(10002);
                            contOutput.add(item.getId());
                        }
                        output.add(tempItem.doAction(ActionState.OPEN, gameContext));
                        output.addAll(contOutput);
                        return output;
                    }
                    else {
                        output.add(10000);
                        output.add(tempItem.getId());
                        output.add(tempItem.doAction(ActionState.OPEN, gameContext));
                        return output;
                    }
                }
                else {
                    output.add(10000);
                    output.add(1125); //item doesn't exist in inv.
                    return output;
                }
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

                if (invItemExists){
                    Door door = gameContext.getCurrentRoom().getSpecificDoor(words.get(2));

                    if (gameContext.getPlayer().hasKey(door.getRequiredKey() )) {
                        door.unlock();
                        output.add(10000);
                        output.add(door.getId());
                        output.add(1120); //successful unlock
                        return output;
                    }
                    else if (!door.isLocked()) {
                        output.add(10000);
                        output.add(door.getId());
                        output.add(1123); // door is already opened
                        return output;
                    }
                    else {
                        output.add(10000);
                        output.add(1126); //wrong key
                        return output;
                    }
                }
                else {
                    output.add(10000);
                    output.add(1125); //item doesn't exist in inv.
                    return output;
                }
            }
            else {
                output.add(10000);
                output.add(2001);
                return output;
            }
        }

        output.add(1062);
        return output;
    }
}
