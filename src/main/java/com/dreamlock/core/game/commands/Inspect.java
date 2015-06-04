package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.*;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inspect implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        CommandUtils commandUtils = new CommandUtils(gameContext);
        Word word = words.get(Sequence.SECOND);

        List<Item> foundItems = new ArrayList<>();

        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (!gameContext.getPlayer().isEmptySlot(equipmentSlot)) {
                Item item = gameContext.getPlayer().getSlot(equipmentSlot);
                if (item.getName().toLowerCase().contains(word.getDescription())) {
                    foundItems.add(item);
                }
            }
        }

        Availability itemAvailability = commandUtils.checkItemAvailability(word, commandUtils.inventoryItems);
        switch (itemAvailability) {
            case NON_EXISTENT:
                outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
            case DUPLICATE:
                outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                break;
            case UNIQUE:
                Item foundItem = commandUtils.getInventoryItem(word);
                if (foundItem.getType().equals(ItemType.ARMOR)) {
                    outputMessages.add(new OutputMessage(foundItem.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(1131, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(Integer.parseInt(foundItem.getStats().get(Stats.DEFENSE).toString()), PrintStyle.NUMBER));
                    outputMessages.add(new OutputMessage(1308, PrintStyle.ONLY_TITLE));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                }
                else if (foundItem.getType().equals(ItemType.WEAPON)) {
                    outputMessages.add(new OutputMessage(foundItem.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(1130, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(Integer.parseInt(foundItem.getStats().get(Stats.ATTACK).toString()), PrintStyle.NUMBER));
                    outputMessages.add(new OutputMessage(1308, PrintStyle.ONLY_TITLE));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                }
                else {
                    outputMessages.add(new OutputMessage(1133, PrintStyle.ONLY_TITLE));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                }
                break;
        }
        return outputMessages;
    }
}
