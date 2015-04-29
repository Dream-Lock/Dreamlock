package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.models.Word;

import java.util.List;
import java.util.Map;

/**
 * Created by Andreas on 29/4/2015.
 */
public class Look implements ICommand  {
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        String roomDescription = gameContext.getCurrentRoom().getDescription();
        System.out.println(roomDescription); //ToDo
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        return null;
    }
}
