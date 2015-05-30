package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.states.IState;

public class CanUnequip implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        context.getPlayer().getInventory().addItem(item);
        context.getPlayer().initializeSlot(item.getEquipmentSlot());
        context.getPlayer().calculateStats();

        return(1402);
    }
}