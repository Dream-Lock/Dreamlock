package com.dreamlock.game.states;

import com.dreamlock.game.IGameContext;

public interface IState {
    int doAction (IGameContext context);
    int doAction (IGameContext context, String object);
}
