package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
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

        List<Item> items = gameContext.getCurrentRoom().containsItems(words);
        items.addAll(gameContext.getPlayer().getInventory().containsItems(words));
        if (items.size() == 1) {
            output.add(10000);
            Container containerItem = (Container) items.get(0);
            output.add(containerItem.getId());   // item to print

            contOutput.add(10002);
            contOutput.add(1124);

            for (Item item : containerItem.getItems()) {
                contOutput.add(10002);
                contOutput.add(item.getId());
            }
            output.add(items.get(0).doActionState("Open", gameContext));
            output.addAll(contOutput);
            return output;
        }
        else  if (items.size() > 1) {
            output.add(10000);
            output.add(2001);
            return output;
        }

        output.add(1062);
        return output;
    }
}
