package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.models.Word;

import java.util.List;
import java.util.Map;

public interface ICommand {
    List<Integer> execute(IGameContext gameContext);

    List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words);
}
