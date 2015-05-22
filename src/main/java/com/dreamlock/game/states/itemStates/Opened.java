package com.dreamlock.game.states.itemStates;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.states.IState;

public class Opened implements IState{
    @Override
    public Integer doAction(IGameContext context) {
        return null;
    }

    @Override
    public Integer doAction(IGameContext context, Item item) {
        return 1123;
    }
}