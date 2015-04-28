package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;

import java.util.List;
import java.util.Objects;

public class Examine implements ICommand {
    @Override
    public Integer execute(IGameContext gameContext) {

        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0 ; i < strings.length ; i++ ) {
            stringBuilder.append(strings[i]);
            stringBuilder.append(" ");
        }
        stringBuilder.setLength(stringBuilder.length()-1);

        List<Item> items = gameContext.getCurrentRoom().getItems();
        for (Item item : items) {
            if (Objects.equals(item.getName().toLowerCase(), stringBuilder.toString())) {
                System.out.println(item.getDescription()); //TODO
                return 1;
            }
        }
        System.out.println("I can't find "+stringBuilder.toString());
        return -1;
    }
}
