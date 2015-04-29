package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Examine implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();

        Word word = words.get(2);
        List<Item> items = gameContext.getCurrentRoom().getItems();
        for (Item item : items) {
            if (Objects.equals(item.getName().toLowerCase(), word.getDescription())) {
                output.add(10001);          // print only description
                output.add(item.getId());   // item to print
                output.add(10003);          // new line
                output.add(10003);          // new line
                return output;
            }
        }
        output.add(10000);          // print only title
        output.add(1020);           // I can't find anything with that name!
        output.add(10002);          // new line
        output.add(10002);          // new line
        return output;
    }
}
