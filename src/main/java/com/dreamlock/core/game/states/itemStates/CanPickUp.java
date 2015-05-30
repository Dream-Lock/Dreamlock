package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.story_parser.items.Container;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.states.IState;

import java.util.List;

public class CanPickUp implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        List<Item> roomItems = context.getCurrentRoom().getItems();
        for (Item item1 : roomItems) {
            if (item1.getType().equals(ItemType.CONTAINER)) {
                Container container = (Container) item1;
                container.removeItem(item);
            }
        }
        context.getCurrentRoom().getItems().remove(item);
        context.getPlayer().getInventory().addItem(item);
        return 1060;
    }
}
