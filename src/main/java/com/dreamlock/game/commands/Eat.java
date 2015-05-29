package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.ItemType;
import com.dreamlock.game.jsonParser.items.Consumable;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Eat implements ICommand{

    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();

        boolean itemExists = gameContext.getPlayer().getInventory().containsItem(words.get(2));

        if (itemExists) {
            int duplicates = gameContext.getPlayer().getInventory().hasDuplicates(words.get(2));
            int itemCount = gameContext.getPlayer().getInventory().getItemCount(words.get(2));
            if (duplicates == 1 || itemCount > 1){
                Item item = gameContext.getPlayer().getInventory().getSpecificItem(words.get(2));
                if (item.getType().equals(ItemType.CONSUMABLE)) {
                    Consumable consumable = (Consumable) item;
                    if (consumable.getState().equals("Food")) {
                        output.add(10006);
                    }
                    else {
                        output.add(10000);
                    }
                }
                else {
                    output.add(10000);
                }

                output.add(item.getId());
                output.add(item.doAction(ActionState.EAT, gameContext));
                return output;
            }
            else {
                output.add(2001);
                return output;
            }
        }

        output.add(1042);
        return output;
    }
}
