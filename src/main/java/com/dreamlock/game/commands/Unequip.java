package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.EquipmentSlot;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Unequip implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();

        List<Item> foundItems = new ArrayList<>();

        Word word = words.get(2);


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

        if (foundItems.size() == 1) {
            output.add(10000);
            output.add(foundItems.get(0).getId());   // item to print
            output.add(foundItems.get(0).doAction(ActionState.UNEQUIP, gameContext));
            return output;
        }
        else  if (foundItems.size() > 1) {
            output.add(10000);
            output.add(2001);
            return output;
        }

        output.add(1042);
        return null;
    }
}
