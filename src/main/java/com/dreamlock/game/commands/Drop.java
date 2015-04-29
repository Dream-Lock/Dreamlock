package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Andreas on 29/4/2015.
 */
public class Drop implements ICommand {
    @Override
    public Integer execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, Map<Integer, Word> words) {
        String object = words.get(2).getDescription();

        List<Item> items = gameContext.getPlayer().getInventory().getItems();
        for (Item item : items) {
            if (Objects.equals(item.getName().toLowerCase(), object)) {
                return item.getStates().get("Drop").doAction(gameContext, item);
            }
        }
        return null;
    }
}
