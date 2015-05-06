package com.dreamlock.game.states;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;

import java.io.Serializable;

public interface IState extends Serializable {
    Integer doAction (IGameContext context);
    Integer doAction (IGameContext context, Item item);
}
