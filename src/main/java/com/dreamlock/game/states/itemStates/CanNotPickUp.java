package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

public class CanNotPickUp implements IState {
    @Override
    public Integer doAction(IGameContext context) {
        return 0;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        System.out.println("Cant pick up " + item.getName() + "!");
        return 0;
    }
}
