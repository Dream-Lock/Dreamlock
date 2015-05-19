package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Drop implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();

        boolean itemExists = gameContext.getPlayer().getInventory().containsItem(words.get(2));

        if (itemExists) {
            output.add(10000);
            int duplicates = gameContext.getPlayer().getInventory().hasDuplicates(words.get(2));
            if (duplicates == 1){
                Item item = gameContext.getPlayer().getInventory().getSpecificItem(words.get(2));
                output.add(item.getId());
                output.add(item.doActionState("Drop", gameContext));
                return output;
            }
            else {
                output.add(2001);
                return output;
            }
        }
//
//        List<Item> foundItems = gameContext.getPlayer().getInventory().containsItems(words.get(2));
//        if (foundItems != null) {
//            output.add(10000);
//            if (foundItems.size() ==1 ) {
//                output.add(foundItems.get(0).getId());
//                output.add(foundItems.get(0).doActionState("Drop",gameContext));
//                return output;
//            }
//            else if (foundItems.size() > 1) {
//                output.add(2001);
//                return output;
//            }
//        }

        output.add(1042);
        return output;
    }
}
