package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.states.IState;

public class CanNotPickUp implements IState {
    @Override
    public int doAction(IGameContext context) {
        return 0;
    }

    @Override
    public int doAction(IGameContext context, String object) {
        return 0;
    }
}
