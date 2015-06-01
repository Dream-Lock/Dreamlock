package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Word;

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
        List<OutputMessage> output = new ArrayList<>();

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
            output.add(new OutputMessage(foundItems.get(0).getId()));   // item to print
            output.add(foundItems.get(0).doAction(ActionState.UNEQUIP, gameContext));
            return output;
        }
        else  if (foundItems.size() > 1) {
            output.add(new OutputMessage(2001));
            return output;
        }

        output.add(new OutputMessage(1042));
        return null;
    }
}
