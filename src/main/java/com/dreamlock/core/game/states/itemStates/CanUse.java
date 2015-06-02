package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.states.IState;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

public class CanUse implements IState {
    @Override
    public OutputMessage doAction(IGameContext context) {
        return null;
    }

    @Override
    public OutputMessage doAction(IGameContext context, Item item) {
        ItemType itemType = item.getType();
        switch (itemType) {
            case CONSUMABLE:
                context.getPlayer().calculateStats();
                context.getPlayer().addStats(item.getStats());
                break;
            default:
                break;
        }
        context.getPlayer().getInventory().removeItem(item);
        return new OutputMessage(item.getId(), PrintStyle.ONLY_EFFECT);
    }
}