package com.dreamlock.core.game.commands;


import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Equip implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }


    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        boolean itemExists = gameContext.getPlayer().getInventory().containsItem(words.get(2));

        if (itemExists) {
            int duplicates = gameContext.getPlayer().getInventory().hasDuplicates(words.get(2));
            int itemCount = gameContext.getPlayer().getInventory().getItemCount(words.get(2));
            if (duplicates == 1 || itemCount>1){
                Item item = gameContext.getPlayer().getInventory().getSpecificItem(words.get(2));
                outputMessages.add(new OutputMessage(item.getId()));
                outputMessages.add(item.doAction(ActionState.EQUIP, gameContext));
                return outputMessages;
            }
            else {
                outputMessages.add(new OutputMessage(2001));
                return outputMessages;
            }
        }
//
//        List<Item> foundItems = gameContext.getPlayer().getInventory().containsItems(words.get(2));
//        if (foundItems.size() == 1) {
//            output.add(10000);
//            output.add(foundItems.get(0).getId());   // item to print
//            output.add(foundItems.get(0).getStates().get("Equip").doAction(gameContext, foundItems.get(0)));
//            return output;
//        }
//        else  if (foundItems.size() > 1) {
//            output.add(10000);
//            output.add(2001);
//            return output;
//        }

        outputMessages.add(new OutputMessage(1042));
        return null;
    }
}

