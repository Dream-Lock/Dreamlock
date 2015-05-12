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

        List<Item> foundItems = gameContext.getPlayer().getInventory().containsItems(words);
        if (foundItems != null) {
            output.add(10000);
            if (foundItems.size() ==1 ) {
                output.add(foundItems.get(0).getId());
                output.add(foundItems.get(0).doActionState("Drop",gameContext));
                return output;
            }
            else if (foundItems.size() > 1) {
                output.add(2001);
                return output;
            }
        }

        output.add(1042);
        return output;
    }
}
