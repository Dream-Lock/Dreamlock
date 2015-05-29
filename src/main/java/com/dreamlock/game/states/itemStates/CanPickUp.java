package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ItemType;
import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

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
