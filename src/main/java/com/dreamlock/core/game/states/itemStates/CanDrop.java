package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.states.IState;

public class CanDrop implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        context.getPlayer().getInventory().removeItem(item);
        context.getCurrentRoom().addItem(item);
        return 1040;
    }
}