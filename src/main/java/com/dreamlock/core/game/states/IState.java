package com.dreamlock.core.game.states;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.story_parser.items.Item;

import java.io.Serializable;

public interface IState extends Serializable {
    OutputMessage doAction (IGameContext context);
    OutputMessage doAction (IGameContext context, Item item);
}
