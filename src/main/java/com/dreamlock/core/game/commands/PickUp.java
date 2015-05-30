package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.models.Word;

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
            if ( (item.getType().equals(ItemType.CONTAINER)) ) {
                Container container = (Container) item;

                if ( !(boolean)container.getStats().get(Stats.LOCKED)) {
                    Item item1 = container.getSpecificItem(words.get(2));
                    if (item1 != null ) {
                        foundItems.add(item1);
                    }
                }

            }
        }
        if (foundItems != null) {
            output.add(10000);
            if (foundItems.size() == 1 ) {
                if (foundItems.get(0).getType().equals(ItemType.MISC)) {
                    output.add(1063);
                }
                else {
                    output.add(foundItems.get(0).getId());
                    output.add(foundItems.get(0).doAction(ActionState.PICK_UP, gameContext));
                }
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
