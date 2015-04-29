package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Odin on 28/4/2015.
 */
public class PickUp implements ICommand {
    @Override
    public Integer execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, Map<Integer, Word> words) {
        String object = words.get(1).getDescription();

        List<Item> items = gameContext.getCurrentRoom().getItems();
        for (Item item : items) {
            if (Objects.equals(item.getName().toLowerCase(), object)) {
               return item.getStates().get("Pick Up").doAction(gameContext, object);
            }
        }
        return null;
    }
}
