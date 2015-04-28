package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;

/**
 * Created by tommy on 28/4/2015.
 */
public interface ICommand {
    void execute(IGameContext gameContext);

    void execute(IGameContext gameContext, String[] strings);
}
