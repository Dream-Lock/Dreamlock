package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.states.IState;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Consumable;
import com.dreamlock.core.story_parser.items.Item;

public class CanEat implements IState {
    @Override
    public OutputMessage doAction(IGameContext context) {
        return null;
    }

    @Override
    public OutputMessage doAction(IGameContext context, Item item) {
        ItemType itemType = item.getType();
        if (itemType.equals(ItemType.CONSUMABLE)) {
            Consumable consumable = (Consumable) item;
            if(consumable.getState().equals("Food")){
                context.getPlayer().calculateStats();
                context.getPlayer().addStats(item.getStats());
            }
        }
        context.getPlayer().getInventory().removeItem(item);
        return new OutputMessage(0, PrintStyle.EMPTY);
    }
}
