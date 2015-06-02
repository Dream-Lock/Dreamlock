package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Unequip implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        List<Item> foundItems = new ArrayList<>();

        Word word = words.get(2);


        Item item = gameContext.getPlayer().getSlot(EquipmentSlot.HEAD);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.CHEST);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.HANDS);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.LEGS);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.FEET);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.MAIN_HAND);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.OFF_HAND);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }

        if (foundItems.size() == 1) {
            outputMessages.add(new OutputMessage(foundItems.get(0).getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));   // item to print
            outputMessages.add(foundItems.get(0).doAction(ActionState.UNEQUIP, gameContext));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }
        else  if (foundItems.size() > 1) {
            outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
            return outputMessages;
        }

        outputMessages.add(new OutputMessage(1042, PrintStyle.ONLY_TITLE));
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        return null;
    }
}
