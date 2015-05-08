package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

import java.util.List;

public class CanOpen implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        if (item.getStats().get("locked").equals(true)) {
            List<Item> inventory = context.getPlayer().getInventory().getItems();

            boolean found = false;
            for (Item tempItem : inventory) {
                if (tempItem.getId().equals(item.getStats().get("match"))) {
                    found = true;
                }
            }
            if (found) {
                return open(context,item);
            }
            else {
                return 1122;
            }
        }
        else {
            return open(context,item);
        }
    }
    private Integer open(IGameContext gameContext, Item item) {
        Container container = (Container)item;
        List<Item> containerItems = container.getItems();
        for (Item containerItem : containerItems) {
            gameContext.getCurrentRoom().addItem(containerItem);
        }
        container.removeItems();
        item = (Item) container;
        item.getStates().put("Open",new Opened());
        return 1120;
    }
}
