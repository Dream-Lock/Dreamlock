package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

/**
 * Created by tommy on 28/4/2015.
 */
public class CanDrop implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {

        context.getPlayer().getInventory().removeItem(item);
        context.getCurrentRoom().addItem(item);
        return null;
    }
}
