package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.List;
import java.util.Map;

public interface ICommand {
    List<Integer> execute(IGameContext gameContext);

    List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words);
}
