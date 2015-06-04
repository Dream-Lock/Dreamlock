package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowInventory implements ICommand{
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        List<OutputMessage> output = new ArrayList<>();
        CommandUtils commandUtils = new CommandUtils(gameContext);
        List<Item> inventory = commandUtils.getInventoryItems();
        if (inventory.isEmpty()) {
            output.add(new OutputMessage(1080, PrintStyle.ONLY_TITLE));
        }
        else {
            output.add(new OutputMessage(1081, PrintStyle.ONLY_TITLE));
            for(Item item: inventory) {
                output.add(new OutputMessage(item.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                output.add(new OutputMessage(0, PrintStyle.ARROW));
                output.add(new OutputMessage(item.getId(), PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));
                output.add(new OutputMessage(0, PrintStyle.BREAK));
            }
        }
        output.add(new OutputMessage(0, PrintStyle.BREAK));
        return output;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
       return null;
    }
}
