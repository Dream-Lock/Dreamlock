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

public class PickUp implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        CommandUtils commandUtils = new CommandUtils(gameContext);
        Word word = words.get(Sequence.SECOND);
        Availability itemAvailability = commandUtils.checkItemAvailability(word, commandUtils.roomItems);

        switch (itemAvailability) {
            case NON_EXISTENT:
                outputMessages.add(new OutputMessage(1062, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
            case DUPLICATE:
                outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
            case UNIQUE:
                Item foundItem = commandUtils.getRoomItem(word);
                outputMessages.add(new OutputMessage(foundItem.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                outputMessages.add(foundItem.doAction(ActionState.PICK_UP, gameContext));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
        }
        return outputMessages;
    }
}
