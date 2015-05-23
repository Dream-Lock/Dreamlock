package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PickUp implements ICommand {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();

        List<Item> foundItems = gameContext.getCurrentRoom().containsItems(words.get(2));

        for (Item item : gameContext.getCurrentRoom().getItems()) {
            if ( (item.getType().equals("Container")) ) {
                Container container = (Container) item;

                if ( !(boolean)container.getStats().get(Stats.LOCKED)) {
                    foundItems.add(container.getSpecificItem(words.get(2)));
                }

            }
        }
        if (foundItems != null) {
            output.add(10000);
            if (foundItems.size() == 1 ) {
                output.add(foundItems.get(0).getId());
                output.add(foundItems.get(0).doAction(ActionState.PICK_UP, gameContext));
                return output;
            }
            else if (foundItems.size() > 1) {
                output.add(2001);
                return output;
            }
        }

        output.add(1062);
        return output;
    }
}
