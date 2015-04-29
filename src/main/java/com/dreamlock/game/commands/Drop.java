package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Andreas on 29/4/2015.
 */
public class Drop implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();
        Word word = words.get(2);
        List<Item> items = gameContext.getPlayer().getInventory().getItems();

        for (Item item : items) {
            if (Objects.equals(item.getName().toLowerCase(), word.getDescription())) {
                output.add(10000);
                output.add(item.getId());
                output.add(item.getStates().get("Drop").doAction(gameContext, item));
                output.add(10002);
                output.add(10002);
                return output;
            }
        }
        output.add(10000);
        output.add(1042);
        output.add(10002);
        output.add(10002);
        return output;
    }
}
