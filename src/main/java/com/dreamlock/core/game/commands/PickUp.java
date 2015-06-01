package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PickUp implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        List<Item> foundItems = gameContext.getCurrentRoom().containsItems(words.get(2));

        for (Item item : gameContext.getCurrentRoom().getItems()) {
            if ( (item.getType().equals(ItemType.CONTAINER)) ) {
                Container container = (Container) item;

                if ( !(boolean)container.getStats().get(Stats.LOCKED)) {
                    Item item1 = container.getSpecificItem(words.get(2));
                    if (item1 != null ) {
                        foundItems.add(item1);
                    }
                }

            }
        }
        if (foundItems != null) {
            if (foundItems.size() == 1 ) {
                if (foundItems.get(0).getType().equals(ItemType.MISC)) {
                    outputMessages.add(new OutputMessage(1063));
                }
                else {
                    outputMessages.add(new OutputMessage(foundItems.get(0).getId()));
                    outputMessages.add(new OutputMessage(foundItems.get(0).doAction(ActionState.PICK_UP, gameContext)));
                }
                return outputMessages;
            }
            else if (foundItems.size() > 1) {
                outputMessages.add(new OutputMessage(2001));
                return outputMessages;
            }
        }

        outputMessages.add(new OutputMessage(1062));
        return outputMessages;
    }
}
