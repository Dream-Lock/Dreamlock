package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
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

        List<Item> items = gameContext.getCurrentRoom().containsItems(words);
        items.addAll(gameContext.getPlayer().getInventory().containsItems(words));
        if (items.size() == 1) {
            output.add(10000);
            output.add(items.get(0).getId());   // item to print
            output.add(items.get(0).doActionState("Open",gameContext));
            return output;
        }
        else  if (items.size() > 1) {
            output.add(10000);
            output.add(2001);
            return output;
        }

        output.add(1062);
        return null;
    }
}
