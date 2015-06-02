package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.states.IState;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

public class CanNotEat implements IState {
    @Override
    public OutputMessage doAction(IGameContext context) {
        return null;
    }

    @Override
    public OutputMessage doAction(IGameContext context, Item item) {
        return new OutputMessage(1503, PrintStyle.ONLY_TITLE);
    }
}
