package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.*;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Open implements ICommand{

    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        Word openingItem = words.get(Sequence.SECOND);          // the door - container
        Word keyItem = words.get(Sequence.FOURTH);              // the key

        CommandUtils commandUtils = new CommandUtils(gameContext);
        Availability containerAvailability = commandUtils.checkItemAvailability(openingItem, commandUtils.roomItems);
        Availability doorAvailability = commandUtils.checkDoorAvailability(openingItem, commandUtils.roomDoors);

        if (words.size() > 2) {                                     // when use open with 4 words

            if (doorAvailability.equals(Availability.NON_EXISTENT)) {
                switch (containerAvailability) {
                    case NON_EXISTENT:
                        outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));
                        break;
                    case UNIQUE:
                        if (keyItem != null) {
                            outputMessages.addAll(openItem(gameContext, openingItem));
                        } else {
                            outputMessages.add(new OutputMessage(1155, PrintStyle.ONLY_TITLE));
                            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                        }
                        break;
                    case DUPLICATE:
                        outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                        break;
                }
            }
            else if (containerAvailability.equals(Availability.NON_EXISTENT)) {
                switch (doorAvailability) {
                    case NON_EXISTENT:
                        outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));
                        break;
                    case UNIQUE:
                        if (keyItem != null) {
                            outputMessages.addAll(openDoor(gameContext, openingItem));
                        } else {
                            outputMessages.add(new OutputMessage(1155, PrintStyle.ONLY_TITLE)); //item doesn't exist in inv.
                            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                        }
                        break;
                    case DUPLICATE:
                        outputMessages.add(new OutputMessage(2002, PrintStyle.ONLY_TITLE)); // duplicates exist
                        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                        break;
                }
            }
        }
        else {          // SIMPLE OPEN COMMAND USED
            if (doorAvailability.equals(Availability.NON_EXISTENT)) {
                switch (containerAvailability) {
                    case NON_EXISTENT:
                        outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));
                        break;
                    case UNIQUE:
                        outputMessages.addAll(openItem(gameContext, openingItem));
                        break;
                    case DUPLICATE:
                        outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                        break;
                }
            }
            else if (containerAvailability.equals(Availability.NON_EXISTENT)) {
                switch (doorAvailability) {
                    case NON_EXISTENT:
                        outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));
                        break;
                    case UNIQUE:
                        outputMessages.addAll(justOpenDoor(gameContext, openingItem));
                        break;
                    case DUPLICATE:
                        outputMessages.add(new OutputMessage(2002, PrintStyle.ONLY_TITLE));
                        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                        break;
                }
            }
        }

        return outputMessages;
    }

    private List<OutputMessage> openItem (IGameContext gameContext, Word itemName) {
        List <OutputMessage> outputMessages =  new ArrayList<>();

        CommandUtils commandUtilsTemp = new CommandUtils(gameContext);
        Item tempItem = commandUtilsTemp.getRoomItem(itemName);

        outputMessages.add(new OutputMessage(tempItem.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));   // item to print
        outputMessages.add(tempItem.doAction(ActionState.OPEN, gameContext));

        if (tempItem.getType().equals(ItemType.CONTAINER)) {
            Container containerItem = (Container) tempItem;

            if (!(boolean) containerItem.getStats().get(Stats.LOCKED)){
                outputMessages.add(new OutputMessage(1154, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));

                for (Item item : containerItem.getItems()) {
                    outputMessages.add(new OutputMessage(item.getId(), PrintStyle.ONLY_TITLE));
                }
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            }
            else {
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            }
        }

        return outputMessages;
    }

    private List<OutputMessage> openDoor (IGameContext gameContext, Word doorName) {
        List <OutputMessage> outputMessages = new ArrayList<>();
        CommandUtils commandUtilsTemp = new CommandUtils(gameContext);
        Door door = commandUtilsTemp.getRoomDoor(doorName);
        if (!door.isLocked()) {
            outputMessages.add(new OutputMessage(door.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(1153, PrintStyle.ONLY_TITLE)); // door is already opened
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }
        else if (gameContext.getPlayer().hasKey(door.getRequiredKey())) {
            door.unlock();
            outputMessages.add(new OutputMessage(door.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(1150, PrintStyle.ONLY_TITLE)); //successful unlock\
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }

        outputMessages.add(new OutputMessage(1156, PrintStyle.ONLY_TITLE)); //wrong key
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        return outputMessages;
    }

    private List<OutputMessage> justOpenDoor (IGameContext gameContext, Word doorName) {
        List <OutputMessage> outputMessages = new ArrayList<>();

        CommandUtils commandUtilsTemp = new CommandUtils(gameContext);
        Door door = commandUtilsTemp.getRoomDoor(doorName);

        if (!door.isLocked()) {
            outputMessages.add(new OutputMessage(door.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(1153, PrintStyle.ONLY_TITLE)); // door is already opened
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }
        outputMessages.add(new OutputMessage(door.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
        outputMessages.add(new OutputMessage(1152, PrintStyle.ONLY_TITLE)); //successful unlock
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        return outputMessages;
    }
}
