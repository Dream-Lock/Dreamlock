package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Door;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Examine implements ICommand {
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
            output.add(10001);          // print only description
            output.add(items.get(0).getId());   // item to print
            return output;
        }
        else  if (items.size() > 1) {
            output.add(10000);
            output.add(2001);
            return output;
        }

        List<Door> doors = gameContext.getCurrentRoom().containsDoors(words);
        if (doors.size() == 1) {
            output.add(10000);
            output.add(doors.get(0).getId());   // item to print
            if (doors.get(0).isLocked()) {
                output.add(2003);
            }
            else {
                output.add(2004);
            }

            return output;
        }
        else  if (doors.size() > 1) {
            output.add(10000);
            output.add(2002);
            return output;
        }
        output.add(10000);          // print only title
        output.add(1020);           // I can't find anything with that name!
        return output;
    }
}
