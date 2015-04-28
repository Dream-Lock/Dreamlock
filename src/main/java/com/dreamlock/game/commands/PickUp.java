package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;

import java.util.List;
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
    public Integer execute(IGameContext gameContext, String[] words) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0 ; i < words.length ; i++ ) {
            stringBuilder.append(words[i]);
            stringBuilder.append(" ");
        }
        stringBuilder.setLength(stringBuilder.length()-1);

        List<Item> items = gameContext.getCurrentRoom().getItems();
        for (Item item : items) {
            if (Objects.equals(item.getName().toLowerCase(), stringBuilder.toString())) {
               item.getStates().get("Pick Up").doAction();
            }
        }
        return null;
    }
}
