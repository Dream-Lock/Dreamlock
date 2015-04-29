package com.dreamlock.game.states;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;

public interface IState {
    Integer doAction (IGameContext context);
    Integer doAction (IGameContext context, Item item);
}
