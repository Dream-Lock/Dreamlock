package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.Map;

public interface ICommand {
    Integer execute(IGameContext gameContext);

    Integer execute(IGameContext gameContext, Map<Integer, Word> words);
}
