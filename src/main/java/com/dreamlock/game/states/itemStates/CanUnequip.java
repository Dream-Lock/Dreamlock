package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

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