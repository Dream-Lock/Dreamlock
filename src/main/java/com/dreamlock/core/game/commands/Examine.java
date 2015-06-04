package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Availability;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Sequence;
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
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        CommandUtils commandUtils = new CommandUtils(gameContext);
        Word word = words.get(Sequence.SECOND);
        List<Item> items = new ArrayList<>();
        boolean isItem = false;
        items.addAll(commandUtils.inventoryItems);
        items.addAll(commandUtils.roomItems);

        Availability itemAvailability = commandUtils.checkItemAvailability(word, items);

        switch (itemAvailability) {
            case NON_EXISTENT:
                isItem = false;
                break;
            case UNIQUE:
                isItem = true;
                Item item = commandUtils.getItem(word);
                if (item.getType().equals(ItemType.CONTAINER)) {            // if item is a container
                    Container containerItem = (Container) item;
                    if (!(boolean) containerItem.getStats().get(Stats.LOCKED)) {
                        outputMessages.add(new OutputMessage(item.getId(), PrintStyle.TITLE_DESCRIPTION));
                        outputMessages.add(new OutputMessage(1154, PrintStyle.ONLY_TITLE));

                        for (Item item1 : containerItem.getItems()) {
                            outputMessages.add(new OutputMessage(item1.getId(), PrintStyle.ONLY_TITLE));
                        }
                    }
                }
                else {                                                      // if item is not a container
                    outputMessages.add(new OutputMessage(item.getId(), PrintStyle.TITLE_DESCRIPTION));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                }
                break;
            case DUPLICATE:
                isItem = true;
                outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
        }

        if (!isItem) {
            Availability doorAvailability = commandUtils.checkDoorAvailability(words.get(Sequence.SECOND), commandUtils.roomDoors);

            switch (doorAvailability) {
                case NON_EXISTENT:
                    outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));           // I can't find anything with that name!
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                    break;
                case UNIQUE:
                    Door door = commandUtils.roomDoors.get(0);
                    outputMessages.add(new OutputMessage(door.getId(), PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));   // item to print
                    if (door.isLocked()) {
                        outputMessages.add(new OutputMessage(2003, PrintStyle.ONLY_TITLE));
                    } else {
                        outputMessages.add(new OutputMessage(2004, PrintStyle.ONLY_TITLE));
                    }
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                    break;
                case DUPLICATE:
                    outputMessages.add(new OutputMessage(2002, PrintStyle.ONLY_TITLE));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                    break;
            }
        }

        return outputMessages;
    }
}
