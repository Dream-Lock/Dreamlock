package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;

/**
 * Created by tommy on 28/4/2015.
 */
public interface ICommand {
    Integer execute(IGameContext gameContext);

    Integer execute(IGameContext gameContext, String[] words);
}
