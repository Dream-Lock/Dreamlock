package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Examine implements ICommand {
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



        List<Item> items = gameContext.getCurrentRoom().containsItems(words);
        items.addAll(gameContext.getPlayer().getInventory().containsItems(words));
        if (items.size() == 1) {
            output.add(10001);          // print only description
            output.add(items.get(0).getId());   // item to print
            return output;
        }
        else  if (items.size() > 1) {
            output.add(10000);
            output.add(2001);
            return output;
        }
        //items.addAll(gameContext.getPlayer().getInventory().containsItems(words));
//        for (Item item : items) {
//            if (item.getName().toLowerCase().contains(word.getDescription())) {
//                itemsFound++;
//                foundItem = item;
//            }
//        }
//        if (items != null) {
//
//        }
//        if (items.size() == 1) {
//            output.add(10001);          // print only description
//            output.add(foundItem.getId());   // item to print
//            return output;
//        }
//        else if (itemsFound > 1) {
//            output.add(10000);
//            output.add(2001);
//            return output;
//        }
        output.add(10000);          // print only title
        output.add(1020);           // I can't find anything with that name!
        return output;
    }
}
