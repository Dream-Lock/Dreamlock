package com.dreamlock.core.game.states.itemStates;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.states.IState;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

public class CanOpen implements IState {
    @Override
    public OutputMessage doAction(IGameContext context) {
        return null;
    }

    @Override
    public OutputMessage doAction(IGameContext context, Item item) {
        if (item.getStats().get(Stats.LOCKED).equals(true)) {
            int keyId = (int) item.getStats().get(Stats.MATCH);

            if (context.getPlayer().hasKey(keyId)) {
                return open(context,item);
            }
            else {
                return new OutputMessage(1152, PrintStyle.ONLY_TITLE);
            }
        }
        else {
            return open(context,item);
        }
    }
    private OutputMessage open(IGameContext gameContext, Item item) {
        item.getStates().put(ActionState.OPEN,new Opened());
        item.getStats().put(Stats.LOCKED,false);
        return new OutputMessage(1150, PrintStyle.ONLY_TITLE);
    }
}