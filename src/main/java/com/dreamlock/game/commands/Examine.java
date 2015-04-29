package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Examine implements ICommand {
    @Override
    public Integer execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, Map<Integer, Word> words) {
        String object = words.get(2).getDescription();

        List<Item> items = gameContext.getCurrentRoom().getItems();
        for (Item item : items) {
            if (Objects.equals(item.getName().toLowerCase(), object)) {
                System.out.println(item.getDescription()); //TODO
                return 1;
            }
        }
        System.out.println("I can't find "+object);
        return -1;
    }
}
