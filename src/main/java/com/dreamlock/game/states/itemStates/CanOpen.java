package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.constants.ActionState;
import com.dreamlock.game.constants.Stats;
import com.dreamlock.game.jsonParser.items.Container;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

import java.util.List;

public class CanOpen implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        if (item.getStats().get(Stats.LOCKED).equals(true)) {
            int keyId = (int) item.getStats().get(Stats.MATCH);

            if (context.getPlayer().hasKey(keyId)) {
                return open(context,item);
            }
            else {
                return 1122;
            }
        }
        else {
            return open(context,item);
        }
    }
    private Integer open(IGameContext gameContext, Item item) {
        item.getStates().put(ActionState.OPEN,new Opened());
        item.getStats().put(Stats.LOCKED,false);
        return 1120;
    }
}