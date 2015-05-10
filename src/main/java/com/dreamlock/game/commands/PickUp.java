package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PickUp implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();
//        Integer itemsFound = 0;
//        Item foundItem = null;
//        Word word = words.get(2);
//
//        List<Item> items = gameContext.getCurrentRoom().getItems();
//        for (Item item : items) {
//            if (item.getName().toLowerCase().contains(word.getDescription())) {
//                itemsFound++;
//                foundItem = item;
//            }
//        }
        List<Item> foundItems = gameContext.getCurrentRoom().containsItems(words);
        if (foundItems != null) {
            output.add(10000);
            if (foundItems.size() ==1 ) {
                output.add(foundItems.get(0).getId());
                output.add(foundItems.get(0).getStates().get("Pick Up").doAction(gameContext, foundItems.get(0)));
                return output;
            }
            else if (foundItems.size() > 1) {
                output.add(2001);
                return output;
            }
        }
//        output.add(10000);
//        if (itemsFound == 1) {
//            output.add(foundItem.getId());
//            output.add(foundItem.getStates().get("Pick Up").doAction(gameContext, foundItem));
//            return output;
//        }
//        else if (itemsFound > 1) {
//            output.add(2001);
//            return output;
//        }

        output.add(1062);
        return output;
    }
}
