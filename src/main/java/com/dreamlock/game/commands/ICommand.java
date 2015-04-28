package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;

public interface ICommand {
    Integer execute(IGameContext gameContext);

    Integer execute(IGameContext gameContext, String[] words);
}
