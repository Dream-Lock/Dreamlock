package com.dreamlock.core.game.commands;


import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.Availability;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Equip implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }


    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        CommandUtils commandUtils = new CommandUtils(gameContext);
        Word word = words.get(Sequence.SECOND);
        Availability itemAvailability = commandUtils.checkItemAvailability(word, commandUtils.inventoryItems);

        switch (itemAvailability) {
            case NON_EXISTENT:
                outputMessages.add(new OutputMessage(1042, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
            case DUPLICATE:
                outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
            case UNIQUE:
                Item foundItem = commandUtils.getInventoryItem(word);
                outputMessages.add(new OutputMessage(foundItem.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                outputMessages.add(foundItem.doAction(ActionState.EQUIP, gameContext));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
        }
        return outputMessages;



//
//        boolean itemExists = gameContext.getPlayer().getInventory().containsItem(words.get(2));
//
//
//
//
//        if (itemExists) {
//            int duplicates = gameContext.getPlayer().getInventory().hasDuplicates(words.get(2));
//            int itemCount = gameContext.getPlayer().getInventory().getItemCount(words.get(2));
//            if (duplicates == 1 || itemCount>1){
//                Item item = gameContext.getPlayer().getInventory().getSpecificItem(words.get(2));
//                outputMessages.add(new OutputMessage(item.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
//                outputMessages.add(item.doAction(ActionState.EQUIP, gameContext));
//                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
//                return outputMessages;
//            }
//            else {
//                outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
//                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
//                return outputMessages;
//            }
//        }
//
//        outputMessages.add(new OutputMessage(1042, PrintStyle.ONLY_TITLE));
//        return outputMessages;
    }
}

