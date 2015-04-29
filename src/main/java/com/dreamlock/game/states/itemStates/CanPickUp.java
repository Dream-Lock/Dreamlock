package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

public class CanPickUp implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        context.getCurrentRoom().getItems().remove(item);
        item.getStates().put("Pick Up",new CanNotPickUp());
        item.getStates().put("Drop",new CanDrop());
        context.getPlayer().getInventory().addItem(item);
        return 1060;
    }
}
