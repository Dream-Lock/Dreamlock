package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowInventory implements ICommand{
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        List<Integer> output = new ArrayList<>();
        List<Item> inventory = gameContext.getPlayer().getInventory().getItems();
        if (inventory.isEmpty()) {
            output.add(10000);
            output.add(1080);
        }
        else {
            output.add(1081);
            for(Item item: inventory) {
                output.add(10003);
                output.add(item.getId());
            }
        }
        return output;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
       return null;
    }
}
