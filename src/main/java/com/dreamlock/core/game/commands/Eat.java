package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Consumable;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Eat implements ICommand{

    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        boolean itemExists = gameContext.getPlayer().getInventory().containsItem(words.get(2));

        if (itemExists) {
            int duplicates = gameContext.getPlayer().getInventory().hasDuplicates(words.get(2));
            int itemCount = gameContext.getPlayer().getInventory().getItemCount(words.get(2));
            if (duplicates == 1 || itemCount > 1){
                Item item = gameContext.getPlayer().getInventory().getSpecificItem(words.get(2));
                if (item.getType().equals(ItemType.CONSUMABLE)) {
                    Consumable consumable = (Consumable) item;
                    if (consumable.getState().equals("Food")) {
                    }
                }

                outputMessages.add(new OutputMessage(item.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                outputMessages.add(item.doAction(ActionState.EAT, gameContext));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                return outputMessages;
            }
            else {
                outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                return outputMessages;
            }
        }

        outputMessages.add(new OutputMessage(1042, PrintStyle.ONLY_TITLE));
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        return outputMessages;
    }
}
