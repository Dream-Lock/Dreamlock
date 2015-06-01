package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowInventory implements ICommand{
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> output = new ArrayList<>();
        List<Item> inventory = gameContext.getPlayer().getInventory().getItems();
        if (inventory.isEmpty()) {
            output.add(new OutputMessage(1080));
        }
        else {
            output.add(new OutputMessage(1081));
            for(Item item: inventory) {
                output.add(new OutputMessage(item.getId()));
            }
        }
        return output;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
       return null;
    }
}
