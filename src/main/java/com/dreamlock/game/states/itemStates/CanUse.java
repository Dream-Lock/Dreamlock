package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

public class CanUse implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        String itemType = item.getType();
        switch (itemType) {
            case "Consumable":
                context.getPlayer().calculateStats();
                context.getPlayer().addStats(item.getStats());
                break;
            default:
                break;
        }
        return 10006;
    }
}