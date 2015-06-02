package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;

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
                    outputMessages.add(new OutputMessage(item.getId(), PrintStyle.TITLE_DESCRIPTION));
                    outputMessages.add(new OutputMessage(1124, PrintStyle.ONLY_TITLE));

                    for (Item item1 : containerItem.getItems()) {
                        outputMessages.add(new OutputMessage(item1.getId(), PrintStyle.TITLE_DESCRIPTION));
                    }

                    return outputMessages;
                }
            }
            outputMessages.add(new OutputMessage(item.getId(), PrintStyle.TITLE_DESCRIPTION));   // item to print
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }
        else  if (items.size() > 1) {
            outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }

        List<Door> doors = gameContext.getCurrentRoom().containsDoors(words.get(2));
        if (doors.size() == 1) {
            outputMessages.add(new OutputMessage(doors.get(0).getId()));   // item to print
            if (doors.get(0).isLocked()) {
                outputMessages.add(new OutputMessage(2003, PrintStyle.ONLY_TITLE));
            }
            else {
                outputMessages.add(new OutputMessage(2004, PrintStyle.ONLY_TITLE));
            }
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));

            return outputMessages;
        }
        else  if (doors.size() > 1) {
            outputMessages.add(new OutputMessage(2002, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }
        outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));           // I can't find anything with that name!
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        return outputMessages;
    }
}
