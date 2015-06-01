package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inspect implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        Word word = words.get(2);

        List<Item> foundItems = gameContext.getPlayer().getInventory().containsItems(words.get(2));

        Item item = gameContext.getPlayer().getSlot(EquipmentSlot.HEAD);
        if(item != null){
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.CHEST);
        if(item != null){
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.MAIN_HAND);
        if(item != null){
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                foundItems.add(item);
            }
        }
        if (foundItems != null) {
            if (foundItems.size() ==1 ) {
                if (foundItems.get(0).getType().equals(ItemType.ARMOR)) {
                    outputMessages.add(new OutputMessage(foundItems.get(0).getId()));
                    outputMessages.add(new OutputMessage(1131));
                    outputMessages.add(new OutputMessage(Integer.parseInt(foundItems.get(0).getStats().get(Stats.DEFENSE).toString())));
                    outputMessages.add(new OutputMessage(1308));
                }
                else if (foundItems.get(0).getType().equals(ItemType.WEAPON)) {
                    outputMessages.add(new OutputMessage(foundItems.get(0).getId()));
                    outputMessages.add(new OutputMessage(1130));
                    outputMessages.add(new OutputMessage(Integer.parseInt(foundItems.get(0).getStats().get(Stats.ATTACK).toString())));
                    outputMessages.add(new OutputMessage(1308));
                }
                return outputMessages;
            }
            else if (foundItems.size() > 1) {
                outputMessages.add(new OutputMessage(2001));
                return outputMessages;
            }
        }
        outputMessages.add(new OutputMessage(1020));           // I can't find anything with that name!
        return outputMessages;
    }


}
