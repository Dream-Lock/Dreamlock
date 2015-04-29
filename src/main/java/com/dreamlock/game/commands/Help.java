package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.Map;

/**
 * Created by Andreas on 29/4/2015.
 */
public class Help implements ICommand  {
    @Override
    public Integer execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public Integer execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
