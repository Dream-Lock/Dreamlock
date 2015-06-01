package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Examine implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        List<Item> items = gameContext.getCurrentRoom().containsItems(words.get(2));
        items.addAll(gameContext.getPlayer().getInventory().containsItems(words.get(2)));
        if (items.size() == 1) {
            Item item = items.get(0);

            if (item.getType().equals(ItemType.CONTAINER)) {
                Container containerItem = (Container) item;
                if (!(boolean) containerItem.getStats().get(Stats.LOCKED)) {
                    outputMessages.add(new OutputMessage(item.getId()));
                    outputMessages.add(new OutputMessage(1124));

                    for (Item item1 : containerItem.getItems()) {
                        outputMessages.add(new OutputMessage(item1.getId()));
                    }

                    return outputMessages;
                }
            }
            outputMessages.add(new OutputMessage(item.getId()));   // item to print
            return outputMessages;
        }
        else  if (items.size() > 1) {
            outputMessages.add(new OutputMessage(2001));
            return outputMessages;
        }

        List<Door> doors = gameContext.getCurrentRoom().containsDoors(words.get(2));
        if (doors.size() == 1) {
            outputMessages.add(new OutputMessage(doors.get(0).getId()));   // item to print
            if (doors.get(0).isLocked()) {
                outputMessages.add(new OutputMessage(2003));
            }
            else {
                outputMessages.add(new OutputMessage(2004));
            }

            return outputMessages;
        }
        else  if (doors.size() > 1) {
            outputMessages.add(new OutputMessage(2002));
            return outputMessages;
        }
        outputMessages.add(new OutputMessage(1020));           // I can't find anything with that name!
        return outputMessages;
    }
}
