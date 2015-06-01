package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.states.IState;

public class CanUse implements IState {
    @Override
    public OutputMessage doAction(IGameContext context) {
        return null;
    }

    @Override
    public OutputMessage doAction(IGameContext context, Item item) {
//        String itemType = item.getType();
//        switch (itemType) {
//            case "Consumable":
//                context.getPlayer().calculateStats();
//                context.getPlayer().addStats(item.getStats());
//                break;
//            default:
//                break;
//        }
//        context.getPlayer().getInventory().removeItem(item);
//        return 10006;
        return null;
    }
}