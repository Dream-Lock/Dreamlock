package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;
import com.dreamlock.core.game.states.IState;

public class CanDrop implements IState {
    @Override
    public OutputMessage doAction(IGameContext context) {
        return null;
    }

    @Override
    public OutputMessage doAction(IGameContext context, Item item) {
        context.getPlayer().getInventory().removeItem(item);
        context.getCurrentRoom().addItem(item);
        return new OutputMessage(1040, PrintStyle.ONLY_TITLE);
    }
}